package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.model.Order;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.request.UpdateOrderRequest;
import com.decagon.safariwebstore.payload.response.OrderResponse;
import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;
import com.decagon.safariwebstore.service.OrderService;
import com.decagon.safariwebstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> viewParticularOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/user/status")
    @Secured("USER")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponse>> getOrdersByStatusUser(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size,
            @RequestParam(name = "status") String status){

        String upCase = status.toUpperCase();
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        PagedOrderByStatusResponse<OrderResponse> orderByStatus = orderService.userGetOrderByStatus(upCase, user, page, size);
        return ResponseEntity.ok(orderByStatus);

    }

    @GetMapping("/admin/status")
    @Secured("ADMIN")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponse>> getOrdersByStatusAdmin(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size,
            @RequestParam(name = "status") String status){

        String upCase = status.toUpperCase();
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        PagedOrderByStatusResponse<OrderResponse> orderByStatus = orderService.adminGetOrderByStatus(upCase, user, page, size);
        return ResponseEntity.ok(orderByStatus);

    }

    @GetMapping("/user")
    @Secured("USER")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponse>> userGetOrdersByUser(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size){
        return ResponseEntity.ok(orderService.userGetOrderByUser(page, size));

    }

    @GetMapping("/admin/{userId}")
    @Secured("ADMIN")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponse>> adminGetOrdersByUser(@PathVariable Long userId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size){
        return ResponseEntity.ok(orderService.adminGetOrderByUser(userId, page, size));

    }

    @PutMapping("/admin/{orderId}")
    @Secured("ADMIN")
    public ResponseEntity<?> adminUpdateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderRequest orderRequest){
        orderService.updateOrderStatus(orderId, orderRequest);
        return new ResponseEntity<>("Product Status Updated Successfully", HttpStatus.OK);
    }

    @PutMapping("/user/{orderId}")
    @Secured({"ADMIN","USER"})
    public ResponseEntity<?> userConfirmOrder(@PathVariable Long orderId){
        orderService.userConfirmOrderStatus(orderId);
        return new ResponseEntity<>("Product Status Updated Successfully", HttpStatus.OK);
    }


}