package com.internet.cinema.controller;

import com.internet.cinema.model.Ticket;
import com.internet.cinema.model.User;
import com.internet.cinema.model.dto.OrderRequestDto;
import com.internet.cinema.model.dto.OrderResponseDto;
import com.internet.cinema.service.OrderService;
import com.internet.cinema.service.ShoppingCartService;
import com.internet.cinema.service.UserService;
import com.internet.cinema.util.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public void completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        User user = userService.get(orderRequestDto.getUserId());
        List<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.get(userId)).stream()
                .map(order -> orderMapper.getOrderResponseDtoFromOrder(order))
                .collect(Collectors.toList());
    }
}
