package com.wzl.dao.movie;
import com.wzl.pojo.Movie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface MovieDao {
    public ArrayList<Movie> getMovies(Connection connection) throws SQLException;
    public int deleteMovie(Connection connection, int id) throws SQLException;
    public int addMovie(Connection connection, int id, String name, String director, String location, String actor, Date filmedTime) throws SQLException;
    public int updateMovie(Connection connection, int id, String name, String director, String location, String actor, Date filmedTime) throws SQLException;
    public Movie getMovieByName(Connection connection, String name) throws SQLException;
}
