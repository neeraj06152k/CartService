package dev.neeraj.cartservice.services;

import dev.neeraj.cartservice.exceptions.CartItemNotFoundException;
import dev.neeraj.cartservice.models.CartItem;
import dev.neeraj.cartservice.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public CartItem addItemToCart(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) throws CartItemNotFoundException {
        CartItem savedCartItem = cartRepository.findByUserIdAndProductIdAndIsDeletedFalse(
                cartItem.getUserId(),
                cartItem.getProductId()
        );
        if(savedCartItem == null){
            throw new CartItemNotFoundException(
                    "Cart item with Product ID " +
                            cartItem.getProductId() +
                            " not found"
            );
        }
        return cartRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getUserCart(long userId) {
        return cartRepository.getUserCartItems(userId);
    }

    @Override
    public void removeItemFromCart(CartItem cartItem) {
        cartRepository.removeItemFromUserCart(cartItem.getUserId(), cartItem.getProductId());
    }

    @Override
    public void clearUserCart(long userId) {
        cartRepository.clearUserCart(userId);
    }
}
