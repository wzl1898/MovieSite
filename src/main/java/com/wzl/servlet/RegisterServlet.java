package com.wzl.servlet;

import com.wzl.pojo.User;
import com.wzl.service.UserService;
import com.wzl.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.wzl.util.Constants;
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id = req.getParameter("id");
       String username = req.getParameter("username");
       String password = req.getParameter("password");
       UserService userService = new UserServiceImpl();
       User user = userService.createUser(username, password, Integer.parseInt(id));
        if (user != null){
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            resp.sendRedirect("/movies");
        }else{
            req.setAttribute("error", "fail create");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
    }
}
