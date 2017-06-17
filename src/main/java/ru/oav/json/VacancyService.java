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
        List<HhVacancy> java = VacancyService.getVacanciesFromApi(100, "java");
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
    private static List<HhVacancy> getVacanciesFromApi(int number, String query) {
        int totalPages = VacancyUtil.getTotalPages(query);
        int counter = 0;
        final List<HhVacancy> result = new ArrayList<HhVacancy>(number);

        for (int i = 0; i < totalPages; i++) {
            if (counter >= number) {
                //тут явно косяк т.к. кол-во значений будет неравно number - пока так оставим
                return result;
            }
            List<HhVacancy> vacancies = VacancyUtil.getVacancies(0, query);
            result.addAll(vacancies);
            counter += vacancies.size();
        }

        return result;

    }
}
