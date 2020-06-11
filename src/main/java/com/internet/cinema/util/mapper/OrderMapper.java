package com.internet.cinema.util.mapper;

import com.internet.cinema.model.Order;
import com.internet.cinema.model.Ticket;
import com.internet.cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDto getOrderResponseDtoFromOrder(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setTicketIds(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }
}
