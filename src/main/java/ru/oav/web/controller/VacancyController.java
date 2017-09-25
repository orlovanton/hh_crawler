package ru.oav.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.oav.dao.Vacancy;
import ru.oav.util.ExperienceEnum;
import ru.oav.service.VacancyService;
import ru.oav.util.PropertyHolder;

import java.util.Collection;

/**
 * Контроллер главной страницы веб-интерфейса
 * Created by antonorlov on 16/06/2017.
 */
@Controller
public class VacancyController {

    public static final int ITEMS_PER_PAGE=20;

    @RequestMapping(value = {"/","/index.html"})
    public String getContests(Model model){
        return index(model,0);
    }

    @RequestMapping("/{pageNum}")
    public String index(Model model, @PathVariable Integer pageNum) {
        Collection<Vacancy> vacancies = VacancyService.getVacancies(pageNum, ITEMS_PER_PAGE);
        model.addAttribute("list", vacancies);
        model.addAttribute("totalPages", VacancyService.getTotalPages(ITEMS_PER_PAGE));
        model.addAttribute("currentPage", pageNum);
        return "index";
    }

    @RequestMapping("/setExperience/{exp}")
    public String setExperience( @PathVariable String exp) {
        VacancyService.deleteAll();
        PropertyHolder.getInstance().setExperience(ExperienceEnum.valueOf(exp));
        VacancyService.updateVacancies();
        return "redirect:/";
    }


}
