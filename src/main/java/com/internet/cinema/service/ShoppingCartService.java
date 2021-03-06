package com.internet.cinema.service;

import com.internet.cinema.model.MovieSession;
import com.internet.cinema.model.ShoppingCart;
import com.internet.cinema.model.User;

public interface ShoppingCartService {
    /**
     * This method is responsible to add a Ticket to the ShoppingCart
     *
     * @param movieSession Contains the information required for a ticket
     * @param user         - user who wan't to buy a ticket for a specific MovieSession
     */
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(User user);
}
