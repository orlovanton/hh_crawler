package ru.af.json;

import com.google.gson.Gson;
import ru.af.entity.HhResponse;
import ru.af.entity.HhVacancy;
import ru.af.formatvacancy.PropertyHolder;
import ru.af.formatvacancy.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class VacancyUtil {

    /**
     * Преобразует вакансии из формата json в список вакнсий в формате HhVacancy
     *
     * @return список вакансий
     */
    private static List<HhVacancy> convertToVacancies(String json) {
        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(json, HhResponse.class);
        return hhResponse.getItems();
    }

    /**
     * Получить кол-во страниц по поисковому запросу
     *
     * @param query поисковой запрос
     * @return кол-во вакансий
     */
    private static int getTotalPages(final String query) {
        final String json = RequestUtil.getVacancies(0, query);

        Gson gson = new Gson();
        HhResponse hhResponse = gson.fromJson(json, HhResponse.class);
        return hhResponse.getPages();
    }

    /**
     * Преобразует лист HHVacancy в лист Vacancy
     *
     * @param list список вакансий в формате HHVacancy
     * @return список вакнсий в формате Vacancy
     */
    public static List<Vacancy> convert(List<HhVacancy> list) {
        List<Vacancy> vacancies = new ArrayList<>();

        for (HhVacancy v : list) {
            Vacancy shortVacancy = new Vacancy();

            if (v.getArea() != null) {
                shortVacancy.setVacancyArea(v.getArea().getName());
            } else {
                shortVacancy.setVacancyArea("Город вакансии не указан");
            }
            shortVacancy.setVacancyExperience("Нет опыта");
            //fixme: остальные варианты опыта
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
            String baseUrl = PropertyHolder.getInstance().BASE_URL;
            shortVacancy.setUrl(baseUrl + v.getId());
            vacancies.add(shortVacancy);
        }

        return vacancies;
    }


    /**
     * Получить список вакансий из API HH
     *
     * @param number максимальное кол-во вакансий
     * @param query  поисковое слово, например java
     * @return список вакансий
     */
    public static List<Vacancy> downloadVacancies(int number, String query) {
        int totalPages = VacancyUtil.getTotalPages(query);
        int counter = 0;
        final List<HhVacancy> result = new ArrayList<>(number);

        for (int i = 0; i < totalPages; i++) {
            if (counter >= number) {
                return VacancyUtil.convert(result);
            }
            String vacanciesJson = RequestUtil.getVacancies(i, query);
            List<HhVacancy> vacancies = VacancyUtil.convertToVacancies(vacanciesJson);
            result.addAll(vacancies);
            counter += vacancies.size();
        }
        return VacancyUtil.convert(result);
    }
}
