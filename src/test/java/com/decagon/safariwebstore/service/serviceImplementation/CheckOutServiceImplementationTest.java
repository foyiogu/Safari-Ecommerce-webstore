package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.model.*;
import com.decagon.safariwebstore.repository.OrderRepository;
import com.decagon.safariwebstore.repository.ShippingAddressRepository;
import com.decagon.safariwebstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;



import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CheckOutServiceImplementationTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ShippingAddressRepository shippingAddressRepository;

    @InjectMocks
    CheckOutServiceImplementation checkOutService;

    @InjectMocks
    OrderResponseDTO orderResponseDTO;

    @Mock
    UserRepository userRepository;



    @InjectMocks
    OrderServiceImplementation orderService;

    @InjectMocks
    UserServiceImplementation userService;




    @InjectMocks
    ModelMapper modelMapper;

    Order order;

    ShippingAddress shippingAddress;
    OrderRequestDTO orderRequestDTO;
    ProductItem productItem;
    Product product;
    CartItem cartItem;


    @BeforeEach
    void setUp() {
        shippingAddress = new ShippingAddress("Emmanuel", "emman@mail.com",
                "Decagon", "Lagos state", "Lagos", "08056522762", true);


    }

    @Test
    void doCheckout() {


        User user = new User("austin", "sam", "austin@gmail.com", "male", "27-11-1999", "password");
        given(userRepository.save(user)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        User savedUser = userService.saveUser(user);

        verify(userRepository).save(any(User.class));



        product = new Product("Denim Shoe", 1500, null, null, null, null, null, null, List.of(user));

        cartItem = new CartItem();

        cartItem.setQuantity(2);
        cartItem.setPrice(1500);
        cartItem.setUser(null);
        cartItem.setId(1L);

        //Shipping Address
        given(shippingAddressRepository.save(shippingAddress)).willAnswer(invocation -> invocation.getArgument(0));
        shippingAddressRepository.save(shippingAddress);
        assertThat(shippingAddress).isNotNull();
        verify(shippingAddressRepository).save(any(ShippingAddress.class));


        orderRequestDTO = new OrderRequestDTO();

        orderRequestDTO.setCardDiscount(0.5);
        orderRequestDTO.setDateOrdered(new Date(1 / 2012));
        orderRequestDTO.setCostOfProducts(3000.00);
        orderRequestDTO.setDeliveryFee(2000.00);
        orderRequestDTO.setIsGift(true);
        orderRequestDTO.setDeliveryMethod("door delivery");
        orderRequestDTO.setShippingAddress(shippingAddress);
        orderRequestDTO.setDateDelivered(null);
        orderRequestDTO.setCartItems(List.of(cartItem.getId()));


        order = new Order();

        order.setCardDiscount(orderRequestDTO.getCardDiscount());
        order.setDateOrdered(orderRequestDTO.getDateDelivered());
        order.setCostOfProducts(order.getCostOfProducts());
        order.setDeliveryFee(order.getDeliveryFee());
        order.setIsGift(orderRequestDTO.getIsGift());
        order.setDeliveryMethod(orderRequestDTO.getDeliveryMethod());
        order.setShippingAddress(orderRequestDTO.getShippingAddress());
        order.setDateDelivered(orderRequestDTO.getDateDelivered());
        order.setCartItems(List.of(cartItem));
        order.setUser(user);


        given(orderRepository.save(order)).willAnswer(invocation -> invocation.getArgument(0));

        Order savedOrder = orderService.saveOrder(order);
        verify(orderRepository).save(any(Order.class));




    }
}