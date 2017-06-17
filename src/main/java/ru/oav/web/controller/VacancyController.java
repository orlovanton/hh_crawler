package ru.oav.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.oav.entity.HhVacancy;
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

        final List<HhVacancy> list = VacancyService.getVacancies(40, "java");

        final List<VacancyDto> dtoList = new ArrayList<>();

        for (HhVacancy hhVacancy : list) {
            dtoList.add(new VacancyDto(hhVacancy));
        }
        model.addAttribute("list", dtoList);

        return "index";
    }

}
