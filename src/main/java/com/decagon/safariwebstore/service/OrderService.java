package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.Order;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.request.UpdateOrderRequest;
import com.decagon.safariwebstore.payload.response.OrderResponse;
import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;

public interface OrderService {
    Order getOrder(Long orderId);
    PagedOrderByStatusResponse<OrderResponse> userGetOrderByStatus(String status, User user, int page, int size);
    PagedOrderByStatusResponse<OrderResponse> adminGetOrderByStatus(String status,User user, int page, int size);
    PagedOrderByStatusResponse<OrderResponse> adminGetOrderByUser(Long userId, Integer page, Integer size);
    PagedOrderByStatusResponse<OrderResponse> userGetOrderByUser(Integer page, Integer size);
    void updateOrderStatus(Long orderId, UpdateOrderRequest orderRequest);
    void userConfirmOrderStatus(Long orderId);
}
