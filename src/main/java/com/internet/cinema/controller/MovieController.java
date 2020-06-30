package com.internet.cinema.controller;

import com.internet.cinema.model.dto.MovieRequestDto;
import com.internet.cinema.model.dto.MovieResponseDto;
import com.internet.cinema.service.MovieService;
import com.internet.cinema.util.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.getMovieFromMovieRequestDto(movieRequestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getMovieResponseDtos() {
        return movieService.getAll().stream()
                .map(movieMapper::getMovieResponseDtoFromMovie)
                .collect(Collectors.toList());
    }
}
