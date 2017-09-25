package ru.oav.dao;

/**
 * Created by PC on 16.06.2017.
 */
public class Vacancy {
    private String id;
    private String vacancyName;
    private String vacancyArea;
    private String vacancySalary;
    private String employer;
    private String url;


    public Vacancy() {
    }


    public Vacancy(String id, String vacancyName, String vacancyArea, String vacancySalary, String employer, String url) {
        this.id = id;
        this.vacancyName = vacancyName;
        this.vacancyArea = vacancyArea;
        this.vacancySalary = vacancySalary;
        this.employer = employer;
        this.url = url;
    }

    public String getVacancyArea() {
        return vacancyArea;
    }

    public void setVacancyArea(String vacancyArea) {
        this.vacancyArea = vacancyArea;
    }

    public String getVacancySalary() {
        return vacancySalary;
    }

    public void setVacancySalary(String vacancySalary) {
        this.vacancySalary = vacancySalary;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        return id.equals(vacancy.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id='" + id + '\'' +
                ", vacancyName='" + vacancyName + '\'' +
                ", vacancyArea='" + vacancyArea + '\'' +
                ", vacancySalary='" + vacancySalary + '\'' +
                ", employer='" + employer + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
