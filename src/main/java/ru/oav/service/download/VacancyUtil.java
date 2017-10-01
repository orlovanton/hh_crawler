package ru.oav.service.download;

import com.google.gson.Gson;
import ru.oav.entity.HhVacancy;

/**
 * Created by antonorlov on 16/06/2017.
 */
@Deprecated
public class VacancyUtil {

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
