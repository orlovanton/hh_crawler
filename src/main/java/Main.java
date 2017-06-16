import ru.oav.entity.HhSalary;
import ru.oav.entity.HhVacancy;
import ru.oav.json.VacancyUtil;

import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class Main {


    //https://github.com/hhru/api
    public static void main(String[] args) {

//        21017972
        List<HhVacancy> vacancies = VacancyUtil.getVacancies();

        for (HhVacancy v : vacancies) {

            HhVacancy vacancy = VacancyUtil.getVacancy(v.getId() + "");

            StringBuilder sb = new StringBuilder(vacancy.getName());
            if (vacancy.getSalary() != null) {
                HhSalary salary = vacancy.getSalary();
                sb.append(" - ");
                sb.append(salary.getFrom());
                sb.append(" - ");
                sb.append(salary.getTo());
                sb.append(" ");
                sb.append(salary.getCurrency());
            } else {
                sb.append(" - ЗП неизвестно");
            }

            System.out.println(sb.toString());
        }

    }
}
