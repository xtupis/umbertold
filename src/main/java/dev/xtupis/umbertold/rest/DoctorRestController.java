package dev.xtupis.umbertold.rest;

import dev.xtupis.umbertold.doctor.DoctorFields;
import dev.xtupis.umbertold.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorRestController {

    private final ParserService parserService;

    @Autowired
    public DoctorRestController(ParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping("/api/search")
    public List<DoctorFields> searchDoctors(@RequestParam String city, @RequestParam String speciality) {
        return parserService.parse(city, speciality);
    }
}