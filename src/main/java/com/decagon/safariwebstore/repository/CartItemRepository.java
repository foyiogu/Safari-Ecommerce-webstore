package com.decagon.safariwebstore.repository;

import com.decagon.safariwebstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
