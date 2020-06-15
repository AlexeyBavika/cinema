package com.internet.cinema.util.mapper;

import com.internet.cinema.model.MovieSession;
import com.internet.cinema.model.dto.MovieSessionRequestDto;
import com.internet.cinema.model.dto.MovieSessionResponseDto;
import com.internet.cinema.service.CinemaHallService;
import com.internet.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallService cinemaHallService;

    public MovieSession getMovieSessionFromMovieSessionRequestDto(
            MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        return movieSession;
    }

    public MovieSessionResponseDto getMovieSessionResponseDtoFromMovieSession(
            MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setLocalDateTime(movieSession.getShowTime());
        return movieSessionResponseDto;
    }
}
