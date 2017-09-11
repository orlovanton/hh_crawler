package ru.oav.json;

import ru.oav.entity.HhVacancy;
import ru.oav.formatvacancy.*;

import java.util.*;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyService {

    public static final String MODE="DB";

    public static List<Vacancy> getVacancies(int number) {
        VacancyReaderInt reader;
        //todo: брать режим из .property файла
        if ("DB".equals(MODE)) {
            reader = new VacancyDBReader();
        } else {
            reader = new VacancyTxtReader();
        }
        return reader.getAllVacancies();
    }

    public static void updateVacancies() {

        //зачитать текущие
        VacancyTxtReader reader = new VacancyTxtReader();
        List<Vacancy> savedVacancies = reader.getAllVacancies();
        //добавить в мапку
        Map<String, Vacancy> map = new HashMap<>();

        for (Vacancy savedVacancy : savedVacancies) {
            map.put(savedVacancy.getId(), savedVacancy);
        }

        //получить вакансии из API
        List<Vacancy> java = VacancyService.downloadVacancies(100, "java");
        VacancyTxtWriter wr = new VacancyTxtWriter();

        //обогатить мапку новыми вакансиями

        for (Vacancy v : java) {
            map.put(v.getId(), v);
        }

        //записать мапку в файл
        Collection<Vacancy> values = map.values();

        List<Vacancy> vacancyList = new ArrayList<>(values);

        wr.insert(vacancyList);
    }

    /**
     * Получить список вакансий
     *
     * @param number максимальное кол-во вакансий
     * @param query  поисковое слово, например java
     * @return список вакансий
     */
    public static List<Vacancy> downloadVacancies(int number, String query) {
        int totalPages = getTotalPages(query);
        int counter = 0;
        final List<HhVacancy> result = new ArrayList<>(number);

        for (int i = 0; i < totalPages; i++) {
            if (counter >= number) {
                //тут явно косяк т.к. кол-во значений будет неравно number - пока так оставим
                return convert(result);
            }
            String vacanciesJson = RequestUtil.getVacancies(0, query);
            List<HhVacancy> vacancies = VacancyUtil.convertToVacancies(vacanciesJson);
            result.addAll(vacancies);
            counter += vacancies.size();
        }

        return convert(result);

    }

    private static List<Vacancy> convert(List<HhVacancy> list){
        List<Vacancy> vacancies = new ArrayList<>();

        for (HhVacancy apiVacancy : list) {
            Vacancy convert = VacancyUtil.convert(apiVacancy);
            vacancies.add(convert);
        }
        return vacancies;
    }
    /**
     * Получить кол-во страниц по поисковому запросу
     *
     * @param query поисковой запрос
     * @return
     */
    private static int getTotalPages(final String query) {
        final String json = RequestUtil.getVacancies(query);
        return VacancyUtil.getTotalPages(json);
    }
}
