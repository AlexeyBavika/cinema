package com.internet.cinema.service;

import com.internet.cinema.model.Order;
import com.internet.cinema.model.Ticket;
import com.internet.cinema.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
