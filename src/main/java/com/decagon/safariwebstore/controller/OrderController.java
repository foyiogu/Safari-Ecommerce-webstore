package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.model.Order;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.OrderResponse;
import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;
import com.decagon.safariwebstore.service.OrderService;
import com.decagon.safariwebstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private UserService userService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> viewParticularOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/ordersByStatus")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponse>> getOrdersByStatus(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size,
            @RequestParam(name = "status") String status){

        String upCase = status.toUpperCase();
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        PagedOrderByStatusResponse<OrderResponse> orderByStatus = orderService.getOrderByStatus(upCase, user, page, size);
        return ResponseEntity.ok(orderByStatus);

    }

}
