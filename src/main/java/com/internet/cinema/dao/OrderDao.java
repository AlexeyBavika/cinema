package com.internet.cinema.dao;

import com.internet.cinema.model.Order;
import com.internet.cinema.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderHistory(User user);
}
