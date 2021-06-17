package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.Order;

public interface OrderService {
    Order getOrder(Long orderId);
}
