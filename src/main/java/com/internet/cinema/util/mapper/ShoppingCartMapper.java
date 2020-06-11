package com.internet.cinema.util.mapper;

import com.internet.cinema.model.ShoppingCart;
import com.internet.cinema.model.Ticket;
import com.internet.cinema.model.dto.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {

    public ShoppingCartResponseDto getShoppingCartResponseDtoFromShoppingCart(
            ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        shoppingCartResponseDto.setShoppingCartId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketIds(shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }
}
