package ru.oav.service.download;

import ru.oav.dao.Vacancy;
import ru.oav.entity.HhVacancy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Асинхронный загрузчик вакансий
 *
 * Created by antonorlov on 01/10/2017.
 */
public class AsyncVacancyDownloader extends BaseVacancyDownloader implements VacancyDownLoader {

    private static AsyncVacancyDownloader instance;

    private AsyncVacancyDownloader() {
    }

    public List<Vacancy> download(String query) {
        int totalPages = getTotalPages(query);
        final List<Vacancy> result = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(totalPages);
        List<Callable<List<Vacancy>>> taskList = new ArrayList<>();
        for (int i = 0; i < totalPages; i++) {
            SinglePageDownloader downloader = new SinglePageDownloader(i, query);
            taskList.add(downloader);
        }
        try {
            List<Future<List<Vacancy>>> futures = executorService.invokeAll(taskList);
            for (Future<List<Vacancy>> future : futures) {
                if (future.isDone()) {
                    result.addAll(future.get());
                } else {
                    System.out.println("future is nit done");
                }
            }
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static AsyncVacancyDownloader getInstance() {
        if (instance == null) {
            instance = new AsyncVacancyDownloader();
        }
        return instance;
    }


    private class SinglePageDownloader implements Callable<List<Vacancy>> {
        private int pageNum;
        private String query;

        public SinglePageDownloader(int pageNum, String query) {
            this.pageNum = pageNum;
            this.query = query;
        }

        @Override
        public List<Vacancy> call() throws Exception {
            String vacanciesJson = RequestUtil.getVacancies(pageNum, query);
            List<HhVacancy> hhVacancies = convertToHhVacancies(vacanciesJson);
            return convert(hhVacancies);
        }
    }

}
