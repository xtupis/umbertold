package dev.xtupis.umbertold.serverlet;

import dev.xtupis.umbertold.doctor.DoctorFields;
import dev.xtupis.umbertold.service.ParserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private final ParserService parserService = new ParserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Получаем параметры из запроса
        String city = request.getParameter("city");
        String speciality = request.getParameter("speciality");

        // Парсим данные
        List<DoctorFields> doctors = parserService.parse(city, speciality);

        // Добавляем список врачей в атрибут запроса
        request.setAttribute("doctors", doctors);

        // Перенаправляем на страницу результатов
        request.getRequestDispatcher("/results.jsp").forward(request, response);
    }
}
