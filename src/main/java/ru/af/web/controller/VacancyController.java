package ru.af.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.af.formatvacancy.Vacancy;
import ru.af.json.VacancyService;

import java.util.List;

/**
 * Контроллер главной страницы веб-интерфейса
 */
@Controller
public class VacancyController {

    @RequestMapping(value = {"/","/index.html"})
    public String getContests(Model model){
        return index(model,0);
    }

    @RequestMapping("/{pageNum}")
    public String index(Model model, @PathVariable Integer pageNum) {
        List<Vacancy> vacancies = VacancyService.getVacancies();
        model.addAttribute("list", vacancies);
        model.addAttribute("totalPages", 8);
        model.addAttribute("currentPage", pageNum);
        return "index";
    }


    @RequestMapping("/update")
    public String index() {
        VacancyService.updateVacancies();
        return "redirect:/";
    }


}
