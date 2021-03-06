package com.internet.cinema.dao;

import com.internet.cinema.model.Movie;
import java.util.List;

public interface MovieDao {

    Movie add(Movie movie);

    Movie get(Long id);

    List<Movie> getAll();
}
