package ru.af.json;

import ru.af.entity.HhVacancy;
import ru.af.formatvacancy.*;
import java.util.*;

/**
 * Предоставляет функции обновления и получения вакансий из/в txt файл
 */
public class VacancyService {

    /**
     * Обновить вакансии (запись новых вакансий, обновление старых и
     * удаление уставевших вакансий)
     */
    public static void updateVacancies() {
        VacancyReaderInt reader;
        VacancyWriterInt writer;
        if ("db".equals(PropertyHolder.getInstance().MODE)) {
            reader = new VacancyDBReader();
            writer = new VacancyDBWriter();
        } else {
            reader = new VacancyTxtReader();
            writer = new VacancyTxtWriter();
        }

        List<Vacancy> savedVacancies = reader.getAllVacancies();
        //получить вакансии из API
        List<Vacancy> downloadedVacancies =
                VacancyService.downloadVacancies(100, "java");

        Map<String, Vacancy> savedVacanciesMap = new HashMap<>();
        //добавить все вакансии в мапку
        for (Vacancy savedVacancy : savedVacancies) {
            savedVacanciesMap.put(savedVacancy.getId(), savedVacancy);
        }
        //удалить устаревшие вакансии
        for (Vacancy vacancy : downloadedVacancies) {
            if (!savedVacanciesMap.containsKey(vacancy.getId())) {
                savedVacanciesMap.remove(vacancy.getId());
                writer.deleteVacancy(vacancy.getId());
            }
        }
        Set<Vacancy> newVacancies = new HashSet<>();
        List<Vacancy> updatedVacancies = new ArrayList<>();

        for (Vacancy downloadedVacancy : downloadedVacancies) {
            if (savedVacanciesMap.containsKey(downloadedVacancy.getId())) {
                if (!downloadedVacancy.equals(
                        savedVacanciesMap.get(downloadedVacancy.getId()))) {
                    updatedVacancies.add(downloadedVacancy);
                }
            } else {
                newVacancies.add(downloadedVacancy);
            }
        }
        //записать мапку в файл или в БД
        writer.insert(newVacancies);
        for (Vacancy vacancy : updatedVacancies) {
            writer.updateVacancy(vacancy);
        }
    }

    /**
     * Получить список вакансий из API HH
     *
     * @param number максимальное кол-во вакансий
     * @param query  поисковое слово, например java
     * @return список вакансий
     */
    private static List<Vacancy> downloadVacancies(int number, String query) {
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

    /**
     * Получичть список вакансий из txt файла
     *
     * @return список вакансий из txt файла
     */
    public static List<Vacancy> getVacancies() {
        VacancyTxtReader reader = new VacancyTxtReader();
        return reader.getAllVacancies();
    }

}
