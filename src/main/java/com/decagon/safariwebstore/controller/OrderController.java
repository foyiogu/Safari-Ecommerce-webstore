package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.dto.OrderResponseDTO;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;
import com.decagon.safariwebstore.service.OrderService;
import com.decagon.safariwebstore.service.UserService;
import com.decagon.safariwebstore.utils.JWTUtil;
import com.decagon.safariwebstore.utils.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> viewParticularOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @GetMapping("/user/status")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponseDTO>> getOrdersByStatusUser(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size,
            @RequestParam(name = "status") String status, HttpServletRequest request){

        String upCase = status.toUpperCase();
        String jwt = MethodUtils.parseJwt(request);
        String email = jwtUtil.extractUserName(jwt);
        User user = userService.findUserByEmail(email);
        PagedOrderByStatusResponse<OrderResponseDTO> orderByStatus = orderService.userGetOrderByStatus(upCase, user, page, size);
        return ResponseEntity.ok(orderByStatus);

    }

    @GetMapping("/admin/status")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponseDTO>> getOrdersByStatusAdmin(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size,
            @RequestParam(name = "status") String status, HttpServletRequest request){

        String upCase = status.toUpperCase();
        String jwt = MethodUtils.parseJwt(request);
        String email = jwtUtil.extractUserName(jwt);
        User user = userService.findUserByEmail(email);
        PagedOrderByStatusResponse<OrderResponseDTO> orderByStatus = orderService.adminGetOrderByStatus(upCase, user, page, size);
        return ResponseEntity.ok(orderByStatus);

    }

    @GetMapping("/user")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponseDTO>> userGetOrdersByUser(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size){
        return ResponseEntity.ok(orderService.userGetOrderByUser(page, size));

    }

    @GetMapping("/admin/{userId}")
    public ResponseEntity<PagedOrderByStatusResponse<OrderResponseDTO>> adminGetOrdersByUser(@PathVariable Long userId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "30") Integer size){
        return ResponseEntity.ok(orderService.adminGetOrderByUser(userId, page, size));

    }

}
