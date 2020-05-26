package com.internet.cinema;

import com.internet.cinema.exception.AuthenticationException;
import com.internet.cinema.lib.Injector;
import com.internet.cinema.model.CinemaHall;
import com.internet.cinema.model.Movie;
import com.internet.cinema.model.MovieSession;
import com.internet.cinema.security.AuthenticationService;
import com.internet.cinema.service.CinemaHallService;
import com.internet.cinema.service.MovieService;
import com.internet.cinema.service.MovieSessionService;
import com.internet.cinema.service.ShoppingCartService;
import com.internet.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.cinema");

    public static void main(String[] args) throws AuthenticationException {
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        Movie pirates = new Movie();
        pirates.setTitle("Pirates of the Caribbean");
        pirates = movieService.add(pirates);
        Movie alien = new Movie();
        alien.setTitle("Alien");
        alien = movieService.add(alien);

        CinemaHallService cinemaHallService = (CinemaHallService) INJECTOR
                .getInstance(CinemaHallService.class);
        CinemaHall greenHall = new CinemaHall();
        greenHall.setCapacity(50);
        greenHall = cinemaHallService.add(greenHall);
        CinemaHall blueHall = new CinemaHall();
        blueHall.setCapacity(777);
        blueHall = cinemaHallService.add(blueHall);

        MovieSession firstSession = new MovieSession();
        firstSession.setCinemaHall(greenHall);
        firstSession.setMovie(pirates);
        MovieSessionService movieSessionService = (MovieSessionService) INJECTOR
                .getInstance(MovieSessionService.class);
        LocalDateTime firstSessionDate = LocalDateTime
                .of(LocalDate.of(25,12,11), LocalTime.of(10,10));
        firstSession.setShowTime(firstSessionDate);
        firstSession = movieSessionService.add(firstSession);
        MovieSession secondService = new MovieSession();
        secondService.setCinemaHall(blueHall);
        secondService.setMovie(alien);
        secondService.setShowTime(LocalDateTime
                .of(24, 11, 10,10,10));
        secondService = movieSessionService.add(secondService);

        System.out.println("All cinema halls : " + cinemaHallService.getAll() + "\n");
        System.out.println("All available sessions : " + movieSessionService
                .findAvailableSessions(pirates.getId(), LocalDate.of(25, 12, 11)));

        AuthenticationService authenticationService = (AuthenticationService) INJECTOR
                .getInstance(AuthenticationService.class);
        UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
        authenticationService.register("email@gmail.com", "123a");
        System.out.println("User by email email@gmail.com : " + userService
                .findByEmail("email@gmail.com"));

        ShoppingCartService shoppingCartService = (ShoppingCartService) INJECTOR
                .getInstance(ShoppingCartService
                        .class);
        System.out.println("Shopping cart of user email@gmail.com : " + shoppingCartService
                .getByUser(userService.findByEmail("email@gmail.com").get()));
        shoppingCartService.addSession(firstSession, userService.findByEmail("email@gmail.com")
                .get());
        System.out.println("Shopping cart of user email@gmail.com : " + shoppingCartService
                .getByUser(userService.findByEmail("email@gmail.com").get()));
    }
}
