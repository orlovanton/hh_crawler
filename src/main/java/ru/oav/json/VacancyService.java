package ru.oav.json;

import ru.oav.entity.HhVacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyService {

    /**
     * Получить список вакансий
     *
     * @param number максимальное кол-во вакансий
     * @param query  поисковое слово, например java
     * @return список вакансий
     */
    public static List<HhVacancy> getVacancies(int number, String query) {
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
