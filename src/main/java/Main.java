import ru.oav.formatvacancy.*;
import ru.oav.json.VacancyService;

import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class Main {


    //https://github.com/hhru/api
    public static void main(String[] args) {

//        VacancyService.updateVacancies();
//        s.updateVacancies();
//        VacancyDBReader reader = new VacancyDBReader();
//        List<Vacancy> allVacancies = reader.getAllVacancies();
//
//        for (Vacancy allVacancy : allVacancies) {
//            System.out.println(allVacancy);
        List<Vacancy> vacancies = VacancyService.downloadVacancies(100,"java");
        VacancyDBWriter wr = new VacancyDBWriter();
        wr.insert(vacancies);


        }
    }


