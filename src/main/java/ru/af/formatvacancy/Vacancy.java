package ru.af.formatvacancy;

/**
 * Класс, содержащий поля (id, имя, з/п, опыт, город) вакансии
 */
public class Vacancy {
    private String vacancyArea;
    private String vacancyExperience;
    private String vacancySalary;
    private String vacancyName;
    private String id;

    public Vacancy() {
    }

    public Vacancy(String id, String vacancyName, String vacancySalary,
                   String vacancyExperience, String vacancyArea) {
        this.vacancyArea = vacancyArea;
        this.vacancyExperience = vacancyExperience;
        this.vacancySalary = vacancySalary;
        this.vacancyName = vacancyName;
        this.id = id;
    }

    public String getVacancyArea() {
        return vacancyArea;
    }

    public void setVacancyArea(String vacancyArea) {
        this.vacancyArea = vacancyArea;
    }

    public String getVacancyExperience() {
        return vacancyExperience;
    }

    public void setVacancyExperience(String vacancyExperience) {
        this.vacancyExperience = vacancyExperience;
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
                "vacancyArea='" + vacancyArea + '\'' +
                ", vacancyExperience='" + vacancyExperience + '\'' +
                ", vacancySalary='" + vacancySalary + '\'' +
                ", vacancyName='" + vacancyName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
