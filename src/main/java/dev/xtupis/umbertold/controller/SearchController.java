package dev.xtupis.umbertold.controller;

import dev.xtupis.umbertold.doctor.DoctorFields;
import dev.xtupis.umbertold.service.ParserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
public class SearchController {

    private final ParserService parserService;

    @Autowired
    public SearchController(ParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping("/search")
    public String searchPage(Model model, @RequestParam String city, @RequestParam String speciality) {
        List<DoctorFields> doctors = parserService.parse(city, speciality);
        model.addAttribute("doctors", doctors);
        return "searchResults"; // Шаблон Thymeleaf
    }
}