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

        String city = request.getParameter("city");
        String speciality = request.getParameter("speciality");

        List<DoctorFields> doctors = parserService.parse(city, speciality);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Результаты поиска</h1>");
            if (doctors.isEmpty()) {
                out.println("<p>Врачи не найдены.</p>");
            } else {
                out.println("<ul>");
                for (DoctorFields d : doctors) {
                    out.printf("<li><b>%s</b> — %s — %s — <a href='%s'>Профиль</a></li>",
                            d.getName(), d.getSpecialization(), d.getClinic(), d.getProfileUrl());
                }
                out.println("</ul>");
            }
            out.println("<a href='/index.html'>Назад</a>");
            out.println("</body></html>");
        }
    }
}
