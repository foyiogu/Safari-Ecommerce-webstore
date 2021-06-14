package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.Order;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.OrderResponse;
import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;
import org.springframework.data.domain.Page;

public interface OrderService {
    PagedOrderByStatusResponse<OrderResponse> getOrderByStatus(String status, User user, int page, int size);
}
