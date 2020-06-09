package com.internet.cinema.service.implementation;

import com.internet.cinema.dao.OrderDao;
import com.internet.cinema.model.Order;
import com.internet.cinema.model.Ticket;
import com.internet.cinema.model.User;
import com.internet.cinema.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(tickets);
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        orderDao.add(order);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
