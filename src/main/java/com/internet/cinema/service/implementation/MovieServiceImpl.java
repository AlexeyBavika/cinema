package com.internet.cinema.service.implementation;

import com.internet.cinema.dao.MovieDao;
import com.internet.cinema.model.Movie;
import com.internet.cinema.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
