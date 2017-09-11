package ru.oav.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.oav.formatvacancy.Vacancy;
import ru.oav.json.VacancyService;

import java.util.List;

/**
 * Контроллер главной страницы веб-интерфейса
 * Created by antonorlov on 16/06/2017.
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
        List<Vacancy> vacancies = VacancyService.getVacancies(20 * pageNum);
        model.addAttribute("list", vacancies);
        model.addAttribute("totalPages", 8);
        model.addAttribute("currentPage", pageNum);
        return "index";
    }


}
