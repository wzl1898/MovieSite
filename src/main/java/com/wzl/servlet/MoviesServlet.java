package com.wzl.servlet;

import com.wzl.pojo.Movie;
import com.wzl.pojo.User;
import com.wzl.service.MovieService;
import com.wzl.service.MovieServiceImpl;
import com.wzl.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MoviesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null && method.equals("getMovieByName")){
            getMovieByName(req, resp);
        }else{
            getMovies(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    public void getMovies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieService movieService = new MovieServiceImpl();
        ArrayList<Movie> movieList = movieService.getMovies();
        req.setAttribute("movieList", movieList);
        User user = (User)req.getSession().getAttribute(Constants.USER_SESSION);
        if (user == null || user.getIsAdmin() == 0){
            req.getRequestDispatcher("movies.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }
    }
    public void getMovieByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieService movieService = new MovieServiceImpl();
        String name = req.getParameter("name");
        Movie movie = movieService.getMovieByName(name);
        req.setAttribute("movie", movie);
        req.getRequestDispatcher("movieDetail.jsp").forward(req,resp);
    }
}
