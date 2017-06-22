package ru.oav.json;

import ru.oav.entity.HhVacancy;
import ru.oav.formatvacancy.Vacancy;
import ru.oav.formatvacancy.VacancyReader;
import ru.oav.formatvacancy.VacancyWriter;

import java.util.*;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyService {

    public static List<Vacancy> getVacancies(int number) {
        VacancyReader reader = new VacancyReader();
        return reader.getAllVacancies();
    }

    public static void updateVacancies() {

        //зачитать текущие
        VacancyReader reader = new VacancyReader();
        List<Vacancy> savedVacancies = reader.getAllVacancies();
        //добавить в мапку
        Map<String, Vacancy> map = new HashMap<>();

        for (Vacancy savedVacancy : savedVacancies) {
            map.put(savedVacancy.getId(), savedVacancy);
        }

        //получить вакансии из API
        List<HhVacancy> java = VacancyService.getVacancies(100, "java");
        VacancyWriter wr = new VacancyWriter();

        //обогатить мапку новыми вакансиями

        for (HhVacancy apiVacancy : java) {
            Vacancy convert = wr.convert(apiVacancy);
            map.put(convert.getId(), convert);
        }

        //записать мапку в файл
        Collection<Vacancy> values = map.values();

        List<Vacancy> vacancyList = new ArrayList<>(values);

        wr.write(vacancyList);
    }

    /**
     * Получить список вакансий
     *
     * @param number максимальное кол-во вакансий
     * @param query  поисковое слово, например java
     * @return список вакансий
     */
    public static List<HhVacancy> getVacancies(int number, String query) {
        int totalPages = getTotalPages(query);
        int counter = 0;
        final List<HhVacancy> result = new ArrayList<>(number);

        for (int i = 0; i < totalPages; i++) {
            if (counter >= number) {
                //тут явно косяк т.к. кол-во значений будет неравно number - пока так оставим
                return result;
            }
            String vacanciesJson = RequestUtil.getVacansies(0, query);
            List<HhVacancy> vacancies = VacancyUtil.convertToVacancies(vacanciesJson);
            result.addAll(vacancies);
            counter += vacancies.size();
        }

        return result;

    }
    /**
     * Получить кол-во страниц по поисковому запросу
     *
     * @param query поисковой запрос
     * @return
     */
    private static int getTotalPages(final String query) {
        final String json = RequestUtil.getVacansies(query);
        return VacancyUtil.getTotalPages(json);
    }
}
