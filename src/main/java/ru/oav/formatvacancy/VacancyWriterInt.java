package ru.oav.formatvacancy;

import ru.oav.entity.HhVacancy;

import java.util.List;

/**
 * Created by PC on 17.06.2017.
 */
public interface VacancyWriterInt {
    void write(List<HhVacancy> list);

    Vacancy convert(HhVacancy v);
}
