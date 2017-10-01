package ru.oav.service.download;

import ru.oav.dao.Vacancy;
import ru.oav.entity.HhVacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Синхронный загрузчик вакансий
 *
 * Created by antonorlov on 01/10/2017.
 */
public class SyncVacancyDownloader  extends BaseVacancyDownloader implements VacancyDownLoader {

    private static SyncVacancyDownloader instance;

    private SyncVacancyDownloader() {
    }

    @Override
    public List<Vacancy> download(String query) {
        int totalPages = getTotalPages(query);
//        int counter = 0;
        final List<HhVacancy> result = new ArrayList<>();

        for (int i = 0; i < totalPages; i++) {
            //fixme: пока без ограниения по макс кол-ву
//            if (number != -1 && counter >= number) {
//                //тут явно косяк т.к. кол-во значений будет неравно number - пока так оставим
//                return convert(result);
//            }

            String vacanciesJson = RequestUtil.getVacancies(i, query);
            List<HhVacancy> vacancies = convertToHhVacancies(vacanciesJson);
            result.addAll(vacancies);
//            counter += vacancies.size();
        }

        return convert(result);
    }

    public static SyncVacancyDownloader getInstance() {
        if (instance == null) {
            instance = new SyncVacancyDownloader();
        }
        return instance;
    }
}
