package dev.neeraj.cartservice.services;

import dev.neeraj.cartservice.exceptions.CartItemNotFoundException;
import dev.neeraj.cartservice.models.CartItem;

import java.util.List;


public interface ICartService {
    CartItem addItemToCart(CartItem cartItem);
    CartItem updateCartItem(CartItem cartItem) throws CartItemNotFoundException;
    List<CartItem> getUserCart(long userId);
    void removeItemFromCart(CartItem cartItem);
    void clearUserCart(long userId);
}
