package dev.neeraj.cartservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserCartDTO {

    private long userId;
    private List<ItemDTO> items;

    @Setter
    @Getter
    public class ItemDTO {
        private long productId;
        private int quantity;
    }
}
