package ru.af.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.af.formatvacancy.Vacancy;
import ru.af.json.VacancyService;

import java.util.List;

/**
 * Контроллер главной страницы веб-интерфейса
 */
@Controller
public class VacancyController {

    @RequestMapping(value = {"/", "/index.html"})
    public String getContests(Model model) {
        List<Vacancy> vacancies = VacancyService.getVacancies();
        if (vacancies == null || vacancies.isEmpty()) {
            VacancyService.updateVacancies();
        }
        vacancies = VacancyService.getVacancies();
        model.addAttribute("list", vacancies);
        return "index";
    }


    @RequestMapping("/update")
    public String index() {
        VacancyService.updateVacancies();
        return "redirect:/";
    }


}
