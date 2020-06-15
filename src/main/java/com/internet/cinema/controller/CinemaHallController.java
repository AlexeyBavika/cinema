package com.internet.cinema.controller;

import com.internet.cinema.model.dto.CinemaHallRequestDto;
import com.internet.cinema.model.dto.CinemaHallResponseDto;
import com.internet.cinema.service.CinemaHallService;
import com.internet.cinema.util.mapper.CinemaHallMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    @Autowired
    private CinemaHallService cinemaHallService;
    @Autowired
    private CinemaHallMapper cinemaHallMapper;

    @PostMapping
    public void addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {

        cinemaHallService.add(cinemaHallMapper
                .getCinemaHallFromCinemaHallRequestDto(cinemaHallRequestDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getCinemaHallResponseDtos() {
        System.out.println(cinemaHallService.getAll());
        return cinemaHallService.getAll().stream()
                .map(cinemaHall -> cinemaHallMapper
                        .getCinemaHallResponseDtoFromCinemaHall(cinemaHall))
                .collect(Collectors.toList());
    }
}
