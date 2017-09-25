package ru.oav.json;

import ru.oav.dao.*;
import ru.oav.entity.HhVacancy;
import ru.oav.util.PropertyHolder;

import java.util.*;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyService {

    public static final String DB_MODE = "db";

    public static Collection<Vacancy> getVacancies(int page, int pageSize) {
        VacancyReader reader = getReader();
        return reader.getVacancies(page, pageSize);
    }


    public static void deleteAll(){
        getWriter().deleteAll();
    }


    public static void updateVacancies() {
        VacancyReader reader = getReader();
        VacancyWriter writer = getWriter();

        Collection<Vacancy> savedVacancies = reader.getAllVacancies();
        //получить вакансии из API
        List<Vacancy> downloadedVacancies = VacancyService.downloadVacancies("java");

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
     * @param query поисковое слово, например java
     * @return список вакансий
     */
    public static List<Vacancy> downloadVacancies(String query) {
        return downloadVacancies(Optional.empty(), query);
    }

    /**
     * Получить список вакансий
     *
     * @param numberOptional максимальное кол-во вакансий
     * @param query          поисковое слово, например java
     * @return список вакансий
     */
    public static List<Vacancy> downloadVacancies(Optional<Integer> numberOptional, String query) {
        int totalPages = getTotalPages(query);
        int counter = 0;
        final List<HhVacancy> result = new ArrayList<>();

        for (int i = 0; i < totalPages; i++) {
            if (numberOptional.isPresent() && counter >= numberOptional.get()) {
                //тут явно косяк т.к. кол-во значений будет неравно number - пока так оставим
                return convert(result);
            }
            String vacanciesJson = RequestUtil.getVacancies(i, query);
            List<HhVacancy> vacancies = VacancyUtil.convertToVacancies(vacanciesJson);
            result.addAll(vacancies);
            counter += vacancies.size();
        }

        return convert(result);

    }

    public static int getTotalPages(int perPage) {
        return getReader().getTotal() / perPage;
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
        final String json = RequestUtil.getVacancies(query);
        return VacancyUtil.getTotalPages(json);
    }


    private static VacancyReader getReader() {
        if (DB_MODE.equals(PropertyHolder.getInstance().getMode())) {
            return new VacancyReaderDb();
        } else {
            return new VacancyReaderTxt();
        }
    }

    private static VacancyWriter getWriter() {
        if (DB_MODE.equals(PropertyHolder.getInstance().getMode())) {
            return new VacancyWriterDb();
        } else {
            return new VacancyWriterTxt();
        }
    }
}
