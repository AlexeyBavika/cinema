package com.internet.cinema.dao;

import com.internet.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall get(Long id);
}
