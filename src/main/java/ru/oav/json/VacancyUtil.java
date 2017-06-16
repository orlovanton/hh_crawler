package ru.oav.json;

import com.google.gson.Gson;
import ru.oav.entity.HhResponse;
import ru.oav.entity.HhVacancy;

import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyUtil {

    public static List<HhVacancy> getVacancies() {
        String vacansies = RequestUtil.getVacansies();

        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(vacansies, HhResponse.class);

        return hhResponse.getItems();
    }

    public static HhVacancy getVacancy(final String id){
        Gson gson = new Gson();

        String vacancyStr = RequestUtil.getVacancy(id);
        HhVacancy vacancy = gson.fromJson(vacancyStr, HhVacancy.class);
        return vacancy;
    }
}
