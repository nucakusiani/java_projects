package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.CreateCategoryDTO;
import org.example.models.Category;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.io.PrintWriter;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException{
        PrintWriter out = res.getWriter();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Category> rows = session.createQuery("FROM Category", Category.class)
                    .setMaxResults(20)
                    .getResultList();
            session.getTransaction().commit();

            ObjectMapper mapper = new ObjectMapper();
            String response = mapper.writeValueAsString(rows);

            res.setHeader("Content-Type", "application/json");
            out.println(response);
        }catch(Exception e){
            res.setStatus(500);
            out.println("Something went wrong");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException{
        ObjectMapper mapper = new ObjectMapper();
        // parse request payload and convert to CreateCategoryDTO instance
        CreateCategoryDTO dto = mapper.readValue(req.getInputStream(), CreateCategoryDTO.class);

        // start a DB session
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a DB transaction
            session.beginTransaction();

            // create a category instance
            Category category = new Category();
            category.setName(dto.name);
            category.setImageUrl(dto.image);

            // write it to DB
            session.persist(category);

            // confirm DB changes
            session.getTransaction().commit();

            res.setStatus(204);
        }
    }
}
