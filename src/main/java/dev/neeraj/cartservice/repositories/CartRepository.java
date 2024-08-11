package dev.neeraj.cartservice.repositories;

import dev.neeraj.cartservice.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {


    CartItem save(@NonNull CartItem cartItem);

    @Transactional
    @Modifying
    @Query("update CartItem c set c.isDeleted = true where c.userId = ?1 and c.isDeleted = false")
    void clearUserCart(@NonNull long userId);

    @Transactional
    @Modifying
    @Query("update CartItem c set c.isDeleted = true where c.userId = ?1 and c.productId = ?2 and c.isDeleted = false")
    void removeItemFromUserCart(@NonNull long userId, @NonNull long productId);

    @Query("select c from CartItem c where c.userId = ?1 and c.isDeleted = false")
    List<CartItem> getUserCartItems(@NonNull long userId);

    CartItem findByUserIdAndProductIdAndIsDeletedFalse(@NonNull long userId, @NonNull long productId);


}
