package ru.af.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.af.formatvacancy.Vacancy;
import ru.af.json.VacancyService;
import ru.af.json.VacancyUtil;

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
        //fixme: умножение - это пока только для проверки что работает
        List<Vacancy> vacancies = VacancyService.getVacancies();
        //fixme: узнать надо ли создавать список с вакансиями  с параметром
        model.addAttribute("list", vacancies);
        model.addAttribute("totalPages", 8);
        model.addAttribute("currentPage", pageNum);
        return "index";
    }


}
