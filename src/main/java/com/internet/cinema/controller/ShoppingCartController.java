package com.internet.cinema.controller;

import com.internet.cinema.model.dto.ShoppingCartRequestDto;
import com.internet.cinema.model.dto.ShoppingCartResponseDto;
import com.internet.cinema.service.MovieSessionService;
import com.internet.cinema.service.ShoppingCartService;
import com.internet.cinema.service.UserService;
import com.internet.cinema.util.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/addmoviesession")
    public void addMovieSession(@RequestParam Long userId,
                                @RequestBody ShoppingCartRequestDto shoppingCartRequestDto) {
        shoppingCartService.addSession(movieSessionService.get(shoppingCartRequestDto
                        .getMovieSessionId()),
                userService.get(userId));
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        return shoppingCartMapper
                .getShoppingCartResponseDtoFromShoppingCart(shoppingCartService
                        .getByUser(userService.get(userId)));
    }
}
