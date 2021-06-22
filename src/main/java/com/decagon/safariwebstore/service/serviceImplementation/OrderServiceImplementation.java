package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.BadRequestException;
import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.ERole;
import com.decagon.safariwebstore.model.Order;
import com.decagon.safariwebstore.model.Role;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.OrderResponse;
import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;
import com.decagon.safariwebstore.repository.OrderRepository;
import com.decagon.safariwebstore.repository.RoleRepository;
import com.decagon.safariwebstore.service.OrderService;
import com.decagon.safariwebstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;
    RoleRepository roleRepository;
    private UserService userService;

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Order not found!");
                });
    }


    @Override
    public PagedOrderByStatusResponse<OrderResponse> userGetOrderByStatus(String status, User user, int page, int size) {

        checkUserRole(ERole.USER, user);
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

            Page<Order> orderPage = orderRepository.findByStatusAndUser(status, user, pageable);

            List<Order> content = orderPage.getNumberOfElements() == 0 ? Collections.emptyList() : orderPage.getContent();

        return ordersPageResponse(content, orderPage);

    }


    @Override
    public PagedOrderByStatusResponse<OrderResponse> adminGetOrderByStatus(String status,User user, int page, int size) {

        checkUserRole(ERole.ADMIN, user);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<Order> orderPage = orderRepository.findByStatus(status, pageable);

        List<Order> content = orderPage.getNumberOfElements() == 0 ? Collections.emptyList() : orderPage.getContent();

        return ordersPageResponse(content, orderPage);

    }

    @Override
    public PagedOrderByStatusResponse<OrderResponse> adminGetOrderByUser(Long userId, Integer page, Integer size) {
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        User userWithId = userService.findUserById(userId);
        checkUserRole(ERole.ADMIN, user);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Order> orderPage = orderRepository.findByUser(userWithId, pageable);
        List<Order> content = orderPage.getNumberOfElements() == 0 ? Collections.emptyList() : orderPage.getContent();
        return ordersPageResponse(content, orderPage);
    }

    @Override
    public PagedOrderByStatusResponse<OrderResponse> userGetOrderByUser(Integer page, Integer size) {
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        checkUserRole(ERole.USER, user);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Order> orderPage = orderRepository.findByUser(user, pageable);
        List<Order> content = orderPage.getNumberOfElements() == 0 ? Collections.emptyList() : orderPage.getContent();
        return ordersPageResponse(content, orderPage);
    }


    public PagedOrderByStatusResponse<OrderResponse> ordersPageResponse(List<Order> list, Page<Order> orderPage){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        list.stream().forEach(order -> {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(order.getId());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setQuantity(order.getQuantity());
            orderResponse.setProductName(order.getProduct().getName());
            orderResponse.setUserId(order.getUser().getId());
            orderResponse.setDateOrdered(order.getCheckOut().getDateOrdered().toString());
            orderResponse.setDateDelivered(order.getCheckOut().getDateDelivered().toString());
            orderResponseList.add(orderResponse);
        });


        return new PagedOrderByStatusResponse<>(
                orderResponseList, orderPage.getNumber(), orderPage.getNumberOfElements(), list.size(),
                orderPage.getTotalPages(), orderPage.isLast()
        );
    }

    public void checkUserRole(ERole role, User user){
        Role userRole = roleRepository.findByName(role)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        Role role1 = user.getRoles().get(0);

        if(role1 != userRole){
            throw new BadRequestException("You don't have access to this link",HttpStatus.UNAUTHORIZED);
        }

    }
}
