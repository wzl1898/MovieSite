package com.wzl.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class updateMovieFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id = req.getParameter("id");
       String name = req.getParameter("name");
       String director = req.getParameter("director");
       String location = req.getParameter("location");
       String actor = req.getParameter("actor");
       String filmedTime = req.getParameter("filmedTime");
       req.setAttribute("id", id);
       req.setAttribute("name", name);
       req.setAttribute("director", director);
       req.setAttribute("location", location);
       req.setAttribute("actor", actor);
       req.setAttribute("filmedTime", filmedTime);
        req.getRequestDispatcher("/updateMovie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
