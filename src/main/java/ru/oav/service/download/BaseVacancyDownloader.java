package ru.oav.service.download;

import com.google.gson.Gson;
import ru.oav.dao.Vacancy;
import ru.oav.entity.HhResponse;
import ru.oav.entity.HhVacancy;
import ru.oav.util.PropertyHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonorlov on 01/10/2017.
 */
public abstract class BaseVacancyDownloader {

    /**
     * Получить кол-во страниц по поисковому запросу
     *
     * @param query поисковой запрос
     * @return
     */
    protected static int getTotalPages(final String query) {
        final String json = RequestUtil.getVacancies(query);
        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(json, HhResponse.class);

        return hhResponse.getPages();
    }


    /**
     * Преобразовать спискок ваансий из HhVacancy в Vacancy
     * @param list
     * @return
     */
    protected static List<Vacancy> convert(List<HhVacancy> list) {
        List<Vacancy> vacancies = new ArrayList<>();

        for (HhVacancy apiVacancy : list) {
            Vacancy convert = convert(apiVacancy);
            vacancies.add(convert);
        }
        return vacancies;
    }

    /**
     * Поучить список вакансий с конкретной страницы
     *
     * @return
     */
    protected static List<HhVacancy> convertToHhVacancies(String json) {
        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(json, HhResponse.class);
        return hhResponse.getItems();
    }


    private static Vacancy convert(HhVacancy v) {
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
