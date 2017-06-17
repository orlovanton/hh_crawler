package ru.oav.json;

import com.google.gson.Gson;
import ru.oav.entity.HhResponse;
import ru.oav.entity.HhVacancy;

import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
class VacancyUtil {

    /**
     * Поучить список вакансий с конкретной страницы
     *
     * @param page номер страницы с 0
     * @return
     */
    public static List<HhVacancy> getVacancies(int page, final String query) {
        String vacansies = RequestUtil.getVacansies(page, query);

        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(vacansies, HhResponse.class);

        return hhResponse.getItems();
    }

    public static int getTotalPages(final String query) {
        String vacansies = RequestUtil.getVacansies(query);

        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(vacansies, HhResponse.class);

        return hhResponse.getPages();
    }

    public static HhVacancy getVacancy(final String id) {
        Gson gson = new Gson();

        String vacancyStr = RequestUtil.getVacancy(id);
        HhVacancy vacancy = gson.fromJson(vacancyStr, HhVacancy.class);
        return vacancy;
    }
}
