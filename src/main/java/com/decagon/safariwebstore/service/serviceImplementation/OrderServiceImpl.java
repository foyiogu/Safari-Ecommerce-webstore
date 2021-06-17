//package com.decagon.safariwebstore.service.serviceImplementation;
//
//import com.decagon.safariwebstore.exceptions.BadRequestException;
//import com.decagon.safariwebstore.model.Order;
//import com.decagon.safariwebstore.model.User;
//import com.decagon.safariwebstore.payload.response.OrderResponse;
//import com.decagon.safariwebstore.payload.response.PagedOrderByStatusResponse;
//import com.decagon.safariwebstore.repository.OrderRepository;
//import com.decagon.safariwebstore.service.OrderService;
//import com.mashape.unirest.http.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//
//    @Override
//    public Order getOrder(Long orderId) {
//        return null;
//    }
//
//    @Override
//    public PagedOrderByStatusResponse<OrderResponse> getOrderByStatus(String status, User user, int page, int size) {
//
//        List<String> stringList = List.of("DELIVERED", "PENDING", "REFUNDED", "CANCELLED");
//
//        if (stringList.contains(status)) {
//
//            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
//
//            Page<Order> orderPage = orderRepository.findByStatusAndUser(status, user, pageable);
//
//            List<Order> content = orderPage.getNumberOfElements() == 0 ? Collections.emptyList() : orderPage.getContent();
//
//            List<OrderResponse> orderResponseList = new ArrayList<>();
//
//            content.stream().forEach(order -> {
//                OrderResponse orderResponse = new OrderResponse();
//                orderResponse.setOrderId(order.getId());
//                orderResponse.setStatus(order.getStatus());
//                orderResponse.setQuantity(order.getQuantity());
//                orderResponse.setProductName(order.getProduct().getName());
//                orderResponse.setUserId(order.getUser().getId());
//                orderResponse.setDateOrdered(order.getCheckOut().getDateOrdered().toString());
//                orderResponse.setDateDelivered(order.getCheckOut().getDateDelivered().toString());
//                orderResponseList.add(orderResponse);
//            });
//
//            System.out.println("THE COLLECTION LIST ISSSSSSS "+ orderResponseList);
//
////            for (Order order : content) {
////                OrderResponse orderResponse = new OrderResponse();
////                orderResponse.setOrderId(order.getId());
////                orderResponse.setStatus(order.getStatus());
////                orderResponse.setQuantity(order.getQuantity());
////                orderResponse.setProductName(order.getProduct().getName());
////                orderResponse.setUserId(order.getCheckOut().getUser().getId());
////                orderResponse.setDateOrdered(order.getCheckOut().getDateOrdered().toString());
////                orderResponse.setDateDelivered(order.getCheckOut().getDateDelivered().toString());
////                orderResponseList.add(orderResponse);
////
////            }
//
//            return new PagedOrderByStatusResponse<>(
//                    orderResponseList, orderPage.getNumber(), orderPage.getNumberOfElements(), content.size(),
//                    orderPage.getTotalPages(), orderPage.isLast()
//            );
//
//        }
//        throw new BadRequestException("Could not find orders of such status", HttpStatus.BAD_REQUEST);
//    }
//}
