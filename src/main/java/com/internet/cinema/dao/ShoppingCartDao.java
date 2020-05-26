package com.internet.cinema.dao;

import com.internet.cinema.model.ShoppingCart;
import com.internet.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
