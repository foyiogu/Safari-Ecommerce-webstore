package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.BadRequestException;
import com.decagon.safariwebstore.model.Order;
import com.decagon.safariwebstore.model.OrderStatus;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.OrderResponse;
import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;
import com.decagon.safariwebstore.repository.OrderRepository;
import com.decagon.safariwebstore.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.decagon.safariwebstore.model.OrderStatus.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public PagedOrderByStatusResponse<OrderResponse> getOrderByStatus(String status, User user, int page, int size) {

        List<String> stringList = List.of("DELIVERED", "PENDING", "REFUNDED", "CANCELLED");

        if (stringList.contains(status)) {

            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

            Page<Order> orderPage = orderRepository.findByStatus(status, pageable);

            List<Order> content = orderPage.getNumberOfElements() == 0 ? Collections.emptyList() : orderPage.getContent();

            List<Order> list = new ArrayList<>();
            for (Order order : content) {
                Long id = order.getCheckOut().getUser().getId();
                if (id == user.getId()) {
                    list.add(order);
                }
            }

            List<OrderResponse> orderResponseList = new ArrayList<>();
            for (Order order : list) {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setOrderId(order.getId());
                orderResponse.setStatus(order.getStatus());
                orderResponse.setQuantity(order.getQuantity());
                orderResponse.setProductName(order.getProduct().getName());
                orderResponse.setUserId(order.getCheckOut().getUser().getId());
                orderResponse.setDateOrdered(order.getCheckOut().getDateOrdered().toString());
                orderResponse.setDateDelivered(order.getCheckOut().getDateDelivered().toString());
                orderResponseList.add(orderResponse);

            }

            return new PagedOrderByStatusResponse<>(
                    orderResponseList, orderPage.getNumber(), orderPage.getNumberOfElements(), list.size(),
                    orderPage.getTotalPages(), orderPage.isLast()
            );

        }
        throw new BadRequestException("Could not find orders of such status", HttpStatus.BAD_REQUEST);
    }
}
