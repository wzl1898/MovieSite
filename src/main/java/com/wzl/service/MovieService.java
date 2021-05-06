package com.wzl.service;
import com.wzl.pojo.Movie;

import java.util.ArrayList;
import java.util.Date;

public interface MovieService {
    public ArrayList<Movie> getMovies();
    public int deleteMovie(int id);
    public int addMovie(int id, String name, String director, String location, String actor, Date filmedTime);
    public int updateMovie(int id, String name, String director, String location, String actor, Date filmedTime);
    public Movie getMovieByName(String name);
}
