package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/api/year")
public class CurrentYear extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();

        PrintWriter out = res.getWriter();
        out.print(String.format("{\"year\": \"%s\",}", now.format(yearFormat)));
        out.flush();
    }
}