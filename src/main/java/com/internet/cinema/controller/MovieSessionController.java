package com.internet.cinema.controller;

import com.internet.cinema.model.dto.MovieSessionRequestDto;
import com.internet.cinema.model.dto.MovieSessionResponseDto;
import com.internet.cinema.service.MovieSessionService;
import com.internet.cinema.util.mapper.MovieSessionMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private MovieSessionMapper movieSessionMapper;

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto
                                            movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper
                .getMovieSessionFromMovieSessionRequestDto(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableMovieSessionResponseDtos(
            @RequestParam Long movieId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return movieSessionService.findAvailableSessions(movieId, date.toLocalDate()).stream()
                .map(movieSession -> movieSessionMapper
                        .getMovieSessionResponseDtoFromMovieSession(movieSession))
                .collect(Collectors.toList());
    }
}
