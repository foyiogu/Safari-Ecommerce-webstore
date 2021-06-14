package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.OrderResponse;
import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;
import com.decagon.safariwebstore.repository.OrderRepository;
import com.decagon.safariwebstore.service.UserService;
import com.decagon.safariwebstore.service.serviceImplementation.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class OrderController {

    private OrderServiceImpl orderService;
    private UserService userService;

    private OrderRepository orderRepository;

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
//git push --set-upstream origin feat_view_order_by_status_B
}

