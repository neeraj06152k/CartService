package dev.neeraj.cartservice.controllers;

import dev.neeraj.cartservice.dtos.CartItemDTO;
import dev.neeraj.cartservice.exceptions.CartItemNotFoundException;
import dev.neeraj.cartservice.models.CartItem;
import dev.neeraj.cartservice.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
