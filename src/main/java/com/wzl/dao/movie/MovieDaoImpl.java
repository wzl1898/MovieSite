package com.wzl.dao.movie;

import com.wzl.dao.BaseDao;
import com.wzl.pojo.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDaoImpl implements MovieDao{
    public ArrayList<Movie> getMovies(Connection connection) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from movies";
        //Movie[] movies = null;
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Object[] param = {};
        if(connection != null){
            resultSet = BaseDao.execute(connection, sql, param, resultSet, preparedStatement);
            while (resultSet.next()){
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setName(resultSet.getString("name"));
                System.out.println(movie.getName());
                movie.setDirector(resultSet.getString("director"));
                movie.setLocation(resultSet.getString("location"));
                movie.setActor(resultSet.getString("actor"));
                movie.setFilmedTime(resultSet.getDate("filmedTime"));
                movieList.add(movie);
            }
            BaseDao.closeResource(null, preparedStatement, resultSet);
        }
        return movieList;
    }

    public int deleteMovie(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "delete from movies where id=?";
        Object[] param = {id};
        int updatedRow = 0;
        if(connection != null){
            updatedRow = BaseDao.execute(connection, sql, param, preparedStatement);
            BaseDao.closeResource(null, preparedStatement, null);
        }
        return updatedRow;
    }

    public int addMovie(Connection connection, int id, String name, String director, String location, String actor, Date filmedTime) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "insert into movies(id, `name`, director, location, actor, filmedTime)" +
                "values(?, ?, ?, ?, ?, ?);";
        Object[] param = {id, name, director, location, actor, filmedTime};
        int updatedRow = 0;
        if(connection != null){
            updatedRow = BaseDao.execute(connection, sql, param, preparedStatement);
            BaseDao.closeResource(null, preparedStatement, null);
        }
        return updatedRow;
    }

    public int updateMovie(Connection connection, int id, String name, String director, String location, String actor, Date filmedTime) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "update movies set name=? , director=? , location=? , actor=? , filmedTime=? where id=?;";
        Object[] param = {name, director, location, actor, filmedTime, id};
        int updatedRow = 0;
        if(connection != null){
            updatedRow = BaseDao.execute(connection, sql, param, preparedStatement);
            BaseDao.closeResource(null, preparedStatement, null);
        }
        return updatedRow;
    }

    public Movie getMovieByName(Connection connection, String name) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from movies where name=?";
        Object[] param = {name};
        Movie movie = new Movie();
        if(connection != null){
            resultSet = BaseDao.execute(connection, sql, param, resultSet, preparedStatement);
            if (resultSet.next()){
                movie.setId(resultSet.getInt("id"));
                movie.setName(resultSet.getString("name"));
                System.out.println(movie.getName());
                movie.setDirector(resultSet.getString("director"));
                movie.setLocation(resultSet.getString("location"));
                movie.setActor(resultSet.getString("actor"));
                movie.setFilmedTime(resultSet.getDate("filmedTime"));
            }
            BaseDao.closeResource(null, preparedStatement, resultSet);
        }
        return movie;
    }
}
