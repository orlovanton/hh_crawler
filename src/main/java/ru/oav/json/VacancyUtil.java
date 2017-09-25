package ru.oav.json;

import com.google.gson.Gson;
import ru.oav.entity.HhResponse;
import ru.oav.entity.HhVacancy;
import ru.oav.dao.Vacancy;

import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyUtil {

    private static final String BASE_URL = "https://spb.hh.ru/vacancy/";

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

    public static int getTotalPages(final String json) {


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
        shortVacancy.setUrl(BASE_URL + v.getId());

        return shortVacancy;
    }
}
