package com.internet.cinema.service.implementation;

import com.internet.cinema.dao.OrderDao;
import com.internet.cinema.dao.ShoppingCartDao;
import com.internet.cinema.lib.Inject;
import com.internet.cinema.lib.Service;
import com.internet.cinema.model.Order;
import com.internet.cinema.model.ShoppingCart;
import com.internet.cinema.model.Ticket;
import com.internet.cinema.model.User;
import com.internet.cinema.service.OrderService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(tickets);
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        orderDao.add(order);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        shoppingCart.setTickets(Collections.emptyList());
        shoppingCartDao.update(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
