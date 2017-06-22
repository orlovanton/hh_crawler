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
}
