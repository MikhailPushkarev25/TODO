package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.Hiber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = Hiber.instOf().findByEmailUser(email);
        if (user != null && password.equals(user.getPassword())) {
            HttpSession sc = req.getSession();
            User admin = new User();
            admin.setName(user.getName());
            admin.setEmail(user.getEmail());
            sc.setAttribute("user", admin);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
