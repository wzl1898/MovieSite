package com.wzl.service;

import com.wzl.dao.BaseDao;
import com.wzl.dao.movie.MovieDao;
import com.wzl.dao.movie.MovieDaoImpl;
import com.wzl.pojo.Movie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MovieServiceImpl implements MovieService{
    private MovieDao movieDao;
    public MovieServiceImpl(){
        movieDao = new MovieDaoImpl();
    }
    public ArrayList<Movie> getMovies() {
        Connection connection = null;
        ArrayList<Movie> movieList = null;
        try {
            connection = BaseDao.getConnection();
            movieList = movieDao.getMovies(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return movieList;
    }

    public int deleteMovie(int id) {
        Connection connection = null;
        int updatedRow = 0;
        try {
            connection = BaseDao.getConnection();
            updatedRow = movieDao.deleteMovie(connection, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return updatedRow;
    }

    public int addMovie(int id, String name, String director, String location, String actor, Date filmedTime) {
        Connection connection = null;
        int updatedRow = 0;
        try {
            connection = BaseDao.getConnection();
            updatedRow = movieDao.addMovie(connection, id, name, director, location, actor, filmedTime);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return updatedRow;
    }

    public int updateMovie(int id, String name, String director, String location, String actor, Date filmedTime) {
        Connection connection = null;
        int updatedRow = 0;
        try {
            connection = BaseDao.getConnection();
            updatedRow = movieDao.updateMovie(connection, id, name, director, location, actor, filmedTime);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return updatedRow;
    }

    public Movie getMovieByName(String name) {
        Connection connection = null;
        Movie movie = null;
        try {
            connection = BaseDao.getConnection();
            movie = movieDao.getMovieByName(connection, name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return movie;
    }
}
