package ru.oav.service;

import com.google.gson.Gson;
import ru.oav.dao.Vacancy;
import ru.oav.entity.HhResponse;
import ru.oav.entity.HhVacancy;
import ru.oav.util.PropertyHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyUtil {
    
    /**
     * Получить список вакансий
     *
     * @param number максимальное кол-во вакансий, -1 - все
     * @param query  поисковое слово, например java
     * @return список вакансий
     */
    static List<Vacancy> downloadVacancies(String query, int number) {
        int totalPages = getTotalPages(query);
        int counter = 0;
        final List<HhVacancy> result = new ArrayList<>();

        for (int i = 0; i < totalPages; i++) {
            if (number != -1 && counter >= number) {
                //тут явно косяк т.к. кол-во значений будет неравно number - пока так оставим
                return convert(result);
            }
            String vacanciesJson = RequestUtil.getVacancies(i, query);
            List<HhVacancy> vacancies = VacancyUtil.convertToVacancies(vacanciesJson);
            result.addAll(vacancies);
            counter += vacancies.size();
        }

        return convert(result);

    }

    /**
     * Поучить список вакансий с конкретной страницы
     *
     * @return
     */
    public static List<HhVacancy> convertToVacancies(String json) {
        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(json, HhResponse.class);
        return hhResponse.getItems();
    }


    private static List<Vacancy> convert(List<HhVacancy> list) {
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
        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(json, HhResponse.class);

        return hhResponse.getPages();
    }


    /**
     * Получить вакансию по ее идентификатору
     *
     * @param id
     * @return
     */
    public static HhVacancy getVacancy(final String id) {
        Gson gson = new Gson();

        String vacancyStr = RequestUtil.getVacancy(id);
        HhVacancy vacancy = gson.fromJson(vacancyStr, HhVacancy.class);
        return vacancy;
    }

    public static Vacancy convert(HhVacancy v) {
        Vacancy shortVacancy = new Vacancy();

        if (v.getArea() != null) {
            shortVacancy.setVacancyArea(v.getArea().getName());
        } else {
            shortVacancy.setVacancyArea("Город вакансии не указан");
        }
        if (v.getSalary() != null) {
            String salary = "";
            if (v.getSalary().getFrom() != 0) {
                salary += "от " + v.getSalary().getFrom() + " ";
            }
            if (v.getSalary().getTo() != 0) {
                salary += "до " + v.getSalary().getTo();
            }
            shortVacancy.setVacancySalary(salary);
        } else {
            shortVacancy.setVacancySalary("размер з/п не указан");
        }
        shortVacancy.setVacancyName(v.getName());
        shortVacancy.setId(String.valueOf(v.getId()));
        if (v.getEmployer() != null) {
            shortVacancy.setEmployer(v.getEmployer().getName());
        }
        String baseUrl = PropertyHolder.getInstance().getBaseUrl();
        shortVacancy.setUrl(baseUrl + v.getId());

        return shortVacancy;
    }
}
