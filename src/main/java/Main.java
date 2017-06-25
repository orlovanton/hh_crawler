import ru.oav.entity.HhVacancy;
import ru.oav.formatvacancy.Vacancy;
import ru.oav.formatvacancy.VacancyReader;
import ru.oav.formatvacancy.VacancyWriter;
import ru.oav.json.VacancyService;

import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class Main {


    //https://github.com/hhru/api
    public static void main(String[] args) {

//        VacancyService.updateVacancies();
        VacancyService s = new VacancyService();
        s.updateVacancies();


//        VacancyReader r = new VacancyReader();
//        List<Vacancy> allVacancies = r.getAllVacancies();
//
//        for (Vacancy allVacancy : allVacancies) {
//            System.out.println(allVacancy);
        }
    }

