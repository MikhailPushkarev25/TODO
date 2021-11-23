package ru.job4j.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.store.Hiber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = Hiber.instOf().findAllCategory();
        req.setAttribute("allCategory", categories);
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] cat = req.getParameterValues("cat");
        String info = req.getParameter("info");
        Item item = Item.of(info);
        Hiber.instOf().addNewItem(item, cat);
        resp.sendRedirect(req.getContextPath() + "/category");
    }
}
