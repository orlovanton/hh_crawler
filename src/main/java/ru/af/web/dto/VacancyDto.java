package ru.af.web.dto;

import org.springframework.util.StringUtils;
import ru.af.entity.HhSalary;
import ru.af.entity.HhVacancy;

/**
 * Класс для отображения
 * Created by antonorlov on 17/06/2017.
 */
public class VacancyDto {

    private String name;
    private String city;
    private String salary;


    public VacancyDto(final HhVacancy vacancy) {
        this.name = vacancy.getName();

        if (vacancy.getArea() != null) {
            this.city = vacancy.getArea().getName();
        }

        if (vacancy.getSalary() != null) {
            HhSalary s = vacancy.getSalary();
            this.salary = s.getFrom() == 0 ? "" : s.getFrom() + "";
            this.salary += StringUtils.isEmpty(this.salary) && s.getTo() != 0 ? "" : " - ";
            this.salary += s.getTo() == 0 ? "" : s.getTo();
            this.salary += s.getCurrency() == null ? "" : " " + s.getCurrency();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
