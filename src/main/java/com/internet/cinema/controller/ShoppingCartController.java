package com.internet.cinema.controller;

import com.internet.cinema.model.dto.ShoppingCartRequestDto;
import com.internet.cinema.model.dto.ShoppingCartResponseDto;
import com.internet.cinema.service.MovieSessionService;
import com.internet.cinema.service.ShoppingCartService;
import com.internet.cinema.service.UserService;
import com.internet.cinema.util.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @PostMapping("/add-movie-session")
    public void addMovieSession(Authentication authentication,
                                @RequestBody ShoppingCartRequestDto shoppingCartRequestDto) {
        shoppingCartService.addSession(movieSessionService.get(shoppingCartRequestDto
                .getMovieSessionId()), userService
                .findByEmail(((UserDetails) authentication.getPrincipal()).getUsername())
                .orElseThrow());
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        return shoppingCartMapper
                .getShoppingCartResponseDtoFromShoppingCart(shoppingCartService
                        .getByUser(userService.findByEmail(((UserDetails) authentication
                                .getPrincipal()).getUsername()).orElseThrow()));
    }
}
