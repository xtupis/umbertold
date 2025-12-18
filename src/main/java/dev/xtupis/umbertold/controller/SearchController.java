package dev.xtupis.umbertold.controller;

import dev.xtupis.umbertold.doctor.DoctorFields;
import dev.xtupis.umbertold.service.ParserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private final ParserService parserService;

    @Autowired
    public SearchController(ParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping("/search")
    public String search(@RequestParam String city, @RequestParam String speciality) {
        // Запускаем парсинг и выводим результат в консоль
        parserService.parse(city, speciality);

        return "Parsing completed. Check console for results.";
    }
}
