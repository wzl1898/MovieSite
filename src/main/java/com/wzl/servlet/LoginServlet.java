package com.wzl.servlet;

import com.wzl.pojo.User;
import com.wzl.service.UserService;
import com.wzl.service.UserServiceImpl;
import com.wzl.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        User user = userService.getLoginUser(username, password);
        if (user != null){
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            resp.sendRedirect("/movies");
        }else{
            req.setAttribute("error", "wrong userCode or password");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
