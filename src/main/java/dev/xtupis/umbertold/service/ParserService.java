package dev.xtupis.umbertold.service;

import dev.xtupis.umbertold.doctor.DoctorFields;

import org.springframework.stereotype.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

@Service
public class ParserService {

    public List<DoctorFields> parse(String city, String speciality) {
        List<DoctorFields> doctors = new ArrayList<>();

        try {
            String url = buildUrl(city, speciality);
            System.out.println("Parsing URL: " + url);

            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10_000)
                    .get();

            Elements cards = document.select("div.b-doctor-card");

            for (Element card : cards.stream().limit(5).toList()) {
                String name = card.select("span.b-doctor-card__name-surname").text();
                String spec = card.select("div.b-doctor-card__spec").text();
                String clinic = card.select("div.b-doctor-card__lpu-info").text();

                String profileUrl = card.select("a.b-doctor-card__name-link").attr("abs:href");
                profileUrl = profileUrl.split("#")[0];

                String rating = parseDoctorRating(profileUrl);

                DoctorFields doctor = new DoctorFields(name, spec, clinic, rating, profileUrl);
                doctors.add(doctor);

                System.out.println("Doctor: " + doctor.getName());
                System.out.println("Clinic: " + doctor.getClinic());
                System.out.println("Rating: " + doctor.getRating());
                System.out.println("Profile URL: " + doctor.getProfileUrl());
                System.out.println("=========================================");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    private String parseDoctorRating(String profileUrl) {
        try {
            Document profileDoc = Jsoup.connect(profileUrl)
                    .userAgent("Mozilla/5.0")
                    .timeout(10_000)
                    .get();

            Element ratingElement = profileDoc.selectFirst("div.text-h5.text--text.font-weight-medium.mr-2");
            if (ratingElement != null) {
                return ratingElement.text().trim();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Нет рейтинга";
    }

    private String buildUrl(String city, String speciality) {
        Map<String, String> cityMap = new HashMap<>();
        cityMap.put("Москва", "moskva");
        cityMap.put("Питер", "sankt-peterburg");
        cityMap.put("Воронеж", "voronezh");

        Map<String, String> specialityMap = new HashMap<>();
        specialityMap.put("Терапевт", "terapevt");
        specialityMap.put("Кардиолог", "kardiolog");
        specialityMap.put("Невролог", "nevrolog");
        specialityMap.put("Хирург", "hirurg");
        specialityMap.put("Офтальмолог", "oftalmolog");

        String cityUrl = cityMap.getOrDefault(city, city.toLowerCase());
        String specUrl = specialityMap.getOrDefault(speciality, speciality.toLowerCase());

        return "https://prodoctorov.ru/" + cityUrl + "/" + specUrl + "/";
    }
}
