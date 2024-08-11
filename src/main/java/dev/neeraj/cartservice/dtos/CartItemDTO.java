package dev.neeraj.cartservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private long userId;
    private long productId;
    private int quantity;
}
