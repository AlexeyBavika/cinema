package com.internet.cinema;

import com.internet.cinema.lib.Injector;
import com.internet.cinema.model.Movie;
import com.internet.cinema.service.MovieService;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
