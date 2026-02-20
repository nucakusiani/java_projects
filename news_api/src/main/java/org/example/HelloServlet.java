package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/api/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        PrintWriter out = res.getWriter();
        out.print("{\"message\": \"Hello, nuca!\"}");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        // Read request body
        StringBuilder body = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        out.print("{\"received\": " + body.toString() + "}");
        out.flush();
    }
}