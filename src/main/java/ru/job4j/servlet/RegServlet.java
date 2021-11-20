package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.Hiber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = Hiber.instOf().findByEmailUser(email);
        if (user != null) {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            HttpSession sc = req.getSession();
            User admin = new User();
            admin.setName(name);
            admin.setEmail(email);
            admin.setPassword(password);
            Hiber.instOf().save(admin);
            sc.setAttribute("user", admin);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}