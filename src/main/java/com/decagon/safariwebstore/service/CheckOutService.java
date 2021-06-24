package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.OrderRequestDTO;
import com.decagon.safariwebstore.model.OrderResponseDTO;

public interface CheckOutService {
    OrderResponseDTO doCheckout(OrderRequestDTO orderRequestDTO);
}
