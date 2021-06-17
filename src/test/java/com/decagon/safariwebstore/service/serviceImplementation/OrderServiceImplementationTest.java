package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.repository.OrderRepository;
import com.decagon.safariwebstore.repository.RoleRepository;
import com.decagon.safariwebstore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplementationTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    private UserService userService;

    OrderServiceImplementation orderServiceUnderTest;

    @BeforeEach
    void setUp() {
        orderServiceUnderTest = new OrderServiceImplementation(orderRepository,roleRepository,userService);
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

}
