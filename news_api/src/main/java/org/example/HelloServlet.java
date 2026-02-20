package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/api/health")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        String userAgent = req.getHeader("user-agent");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        PrintWriter out = res.getWriter();
        out.print(String.format("{\"ok\": true, \"userAgent\": \"%s\", \"date\": \"%s\"}", userAgent, now.format(dateFormat)));
        out.flush();
    }
}