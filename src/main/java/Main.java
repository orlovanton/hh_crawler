import ru.oav.entity.HhVacancy;
import ru.oav.json.VacancyService;

import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class Main {


    //https://github.com/hhru/api
    public static void main(String[] args) {

        List<HhVacancy> java = VacancyService.getVacancies(100, "java");
        int i = 0;
        for (HhVacancy hhVacancy : java) {
            System.out.println(i + " - " + hhVacancy.getName() + " " + hhVacancy.getSalary());
            i++;
        }
    }
}
