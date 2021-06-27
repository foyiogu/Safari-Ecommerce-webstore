package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.Order;
import com.decagon.safariwebstore.model.Product;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.repository.OrderRepository;
import com.decagon.safariwebstore.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplementationTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    RoleRepository roleRepository;

    private ModelMapper modelMapper;

    OrderServiceImplementation orderServiceUnderTest;

    @Mock
    private Product product;

    @Mock
    Page<Order> orderPage;

    @BeforeEach
    void setUp() {
        orderServiceUnderTest = new OrderServiceImplementation(orderRepository,roleRepository, modelMapper);
    }

    @Test
    void willThrowExceptionOrderNotFound () {
        assertThatThrownBy(() -> orderServiceUnderTest.getOrder(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Order not found!");
    }

    @Test
    void willNotFindOrder () {
        verify(orderRepository, never()).findById(1L);
    }

    @Test
    void shouldGetOrdersByStatus() {

        User user = new User
                ( "austin", "sam", "austin@gmail.com", "male", "27-11-1999", null);


//        when(checkOut.getId()).thenReturn(100L);
        when(product.getId()).thenReturn(200L);

        Order order = new Order();
//        order.setProduct(product);
//        order.setCheckOut(checkOut);
        order.setStatus("DELIVERED");
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        order.setQuantity("5");

        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "createdAt");

//        assertThat(order.getProduct().getId()).isEqualTo(200L);
//        assertThat(order.getCheckOut().getId()).isEqualTo(100L);

        given(orderRepository.save(order)).willAnswer(invocation -> invocation.getArgument(0));

        Order save = orderRepository.save(order);
        verify(orderRepository).save(any(Order.class));


        assertThat(save.getQuantity()).isEqualTo("5");

        String staus2 = "DELIVERED";

        when(orderRepository.findByStatusAndUser(staus2, user, pageable)).thenReturn(orderPage);

        orderPage = orderRepository.findByStatusAndUser(staus2, user, pageable);
        assertThat(orderPage).isNotNull();


    }

}
