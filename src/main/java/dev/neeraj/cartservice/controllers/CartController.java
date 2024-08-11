package dev.neeraj.cartservice.controllers;

import dev.neeraj.cartservice.dtos.CartItemDTO;
import dev.neeraj.cartservice.dtos.UserCartDTO;
import dev.neeraj.cartservice.exceptions.CartItemNotFoundException;
import dev.neeraj.cartservice.models.CartItem;
import dev.neeraj.cartservice.services.ICartService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/add")
    public CartItem addItemToCart(@RequestBody CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDTO.getProductId());
        cartItem.setUserId(cartItemDTO.getUserId());
        cartItem.setQuantity(cartItemDTO.getQuantity());

        return cartService.addItemToCart(cartItem);
    }

    @PostMapping("/updateItem")
    public CartItem updateCartItem(@RequestBody CartItemDTO cartItemDTO) throws CartItemNotFoundException {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDTO.getProductId());
        cartItem.setUserId(cartItemDTO.getUserId());
        cartItem.setQuantity(cartItemDTO.getQuantity());

        return cartService.updateCartItem(cartItem);
    }

    @PostMapping("/removeItem")
    public void removeItemFromCart(@RequestBody CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDTO.getProductId());
        cartItem.setUserId(cartItemDTO.getUserId());

        cartService.removeItemFromCart(cartItem);
    }

    @GetMapping("/checkoutCart")
    public void clearUserCart(@RequestParam("userId") long userId) {
        cartService.clearUserCart(userId);
    }

    @GetMapping("/getUserCart")
    public ResponseEntity<UserCartDTO> getUserCart(@RequestParam("userId") long userId) {
        List<CartItem> userCart = cartService.getUserCart(userId);
        UserCartDTO userCartDTO = new UserCartDTO();
        userCartDTO.setUserId(userId);

        userCartDTO.setItems(
                userCart.stream()
                        .map(cartItem -> {
                    UserCartDTO.ItemDTO itemDTO = userCartDTO.new ItemDTO();
                    itemDTO.setProductId(cartItem.getProductId());
                    itemDTO.setQuantity(cartItem.getQuantity());
                    return itemDTO;
                        }
                )
                .toList());

        return ResponseEntity.ok(userCartDTO);
    }
}
