package ru.oav.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.oav.entity.HhVacancy;
import ru.oav.formatvacancy.Vacancy;
import ru.oav.json.VacancyService;
import ru.oav.web.dto.VacancyDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonorlov on 16/06/2017.
 */
@Controller
public class VacancyController {

    @RequestMapping("/")
    public String index(Model model) {

        List<Vacancy> vacancies = VacancyService.getVacancies(20);
//        for (Vacancy vacancy:    vacancies ) {
//            System.out.println(vacancy.getVacancyName());
//            System.out.println(vacancy.getVacancyArea());
//            System.out.println(vacancy.getVacancySalary());
//            System.out.println(vacancy.getVacancyExperience());
//
//        }
        model.addAttribute("list", vacancies);
        return "index";
    }


}
