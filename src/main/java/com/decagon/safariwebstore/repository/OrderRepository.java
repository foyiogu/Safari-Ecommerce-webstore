package com.decagon.safariwebstore.repository;

import com.decagon.safariwebstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
