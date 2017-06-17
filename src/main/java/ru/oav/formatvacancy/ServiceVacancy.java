package ru.oav.formatvacancy;

import ru.oav.json.VacancyService;

/**
 * Created by PC on 16.06.2017.
 */
public class ServiceVacancy {
    public static void main(String[] args) {
        VacancyWriter writer = new VacancyWriter();

        writer.write(VacancyService.getVacancies(20, "java"));

    }
}
