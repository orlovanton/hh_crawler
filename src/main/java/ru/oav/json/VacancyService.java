package ru.oav.json;

import ru.oav.entity.HhVacancy;
import ru.oav.formatvacancy.*;

import java.util.*;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyService {


    public static List<Vacancy> getVacancies(int number) {
        VacancyTxtReader reader = new VacancyTxtReader();
        return reader.getAllVacancies();
    }


    public static void updateVacancies() {
        VacancyReaderInt reader;
        VacancyWriterInt writer;
        if ("db".equals(Constanses.MODE)) {
            reader = new VacancyDBReader();
            writer = new VacancyDBWriter();
        } else {
            reader = new VacancyTxtReader();
            writer = new VacancyTxtWriter();
        }

        List<Vacancy> savedVacancies = reader.getAllVacancies();
        //получить вакансии из API
        List<Vacancy> downloadedVacancies = VacancyService.downloadVacancies(100, "java");

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
        //todo: с ХХ иногда затягиваются вакансии с одинаковым идентификатором
        Set<Vacancy> newVacancies = new HashSet<>();
        List<Vacancy> updatedVacancies = new ArrayList<>();
        for (Vacancy downloadedVacancy : downloadedVacancies) {
            if (savedVacanciesMap.containsKey(downloadedVacancy.getId())) {
                if (!downloadedVacancy.equals(savedVacanciesMap.get(downloadedVacancy.getId()))) {
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
     * Получить список вакансий
     *
     * @param number максимальное кол-во вакансий
     * @param query  поисковое слово, например java
     * @return список вакансий
     */
    public static List<Vacancy> downloadVacancies(int number, String query) {
        int totalPages = getTotalPages(query);
        int counter = 0;
        final List<HhVacancy> result = new ArrayList<>(number);

        for (int i = 0; i < totalPages; i++) {
            if (counter >= number) {
                //тут явно косяк т.к. кол-во значений будет неравно number - пока так оставим
                return convert(result);
            }
            String vacanciesJson = RequestUtil.getVacansies(i, query);
            List<HhVacancy> vacancies = VacancyUtil.convertToVacancies(vacanciesJson);
            result.addAll(vacancies);
            counter += vacancies.size();
        }

        return convert(result);

    }

    private static List<Vacancy> convert(List<HhVacancy> list) {
        List<Vacancy> vacancies = new ArrayList<>();

        for (HhVacancy apiVacancy : list) {
            Vacancy convert = VacancyUtil.convert(apiVacancy);
            vacancies.add(convert);
        }
        return vacancies;
    }

    /**
     * Получить кол-во страниц по поисковому запросу
     *
     * @param query поисковой запрос
     * @return
     */
    private static int getTotalPages(final String query) {
        final String json = RequestUtil.getVacansies(query);
        return VacancyUtil.getTotalPages(json);
    }
}
