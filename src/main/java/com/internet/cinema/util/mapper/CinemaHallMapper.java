package com.internet.cinema.util.mapper;

import com.internet.cinema.model.CinemaHall;
import com.internet.cinema.model.dto.CinemaHallRequestDto;
import com.internet.cinema.model.dto.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {

    public CinemaHallResponseDto getCinemaHallResponseDtoFromCinemaHall(
            CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }

    public CinemaHall getCinemaHallFromCinemaHallRequestDto(
            CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHall;
    }
}
