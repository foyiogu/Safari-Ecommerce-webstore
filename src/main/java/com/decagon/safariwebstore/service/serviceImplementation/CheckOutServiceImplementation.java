package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.*;
import com.decagon.safariwebstore.repository.*;
import com.decagon.safariwebstore.security.service.UserDetailsImpl;
import com.decagon.safariwebstore.service.AddressService;
import com.decagon.safariwebstore.service.CheckOutService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor

public class CheckOutServiceImplementation implements CheckOutService {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final AddressService addressService;

    private final AddressRepository addressRepository;

    private final CartItemRepository cartItemRepository;

    private final ShippingAddressRepository shippingAddressRepository;

    private final ModelMapper modelMapper;



    public OrderResponseDTO doCheckout(OrderRequestDTO request) {
        Order order = buildOrder(request);
        Address currentAddress = modelMapper.map(request.getShippingAddress(), Address.class);

        if (request.getShippingAddress().getIsDefaultShippingAddress()) setAsDefaultAddress(currentAddress, loggedInUser());

        Order savedOrder = orderRepository.save(order);
        ShippingAddress shippingAddress = modelMapper.map(request.getShippingAddress(), ShippingAddress.class);

        shippingAddressRepository.save(shippingAddress);



        return buildOrderResponse(savedOrder, request);
    }

    private User loggedInUser(){
        final UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername()).orElseThrow();
    }

    private List<CartItem> getCartItems(OrderRequestDTO request){
        return request
                .getCartItems()
                .stream()
                .map(cartItem -> cartItemRepository.findById(cartItem).orElseThrow(() -> {
                    throw new ResourceNotFoundException("CartItem not found or already paid for");
                }))
                .collect(Collectors.toList());
    }

    private void setAsDefaultAddress(Address currentAddress, User user) {
        if (addressService.isAddressExisting(currentAddress, user)) {
            Address existingAddress = addressService.getAUserAddress(
                    user,
                    currentAddress.getAddress(),
                    currentAddress.getCity(),
                    currentAddress.getState()
            );

            Address userDefaultAddress = addressService.getUserDefaultAddress(loggedInUser());
            userDefaultAddress.setIsDefaultShippingAddress(false);
            addressService.saveAddress(userDefaultAddress, loggedInUser());

            existingAddress.setIsDefaultShippingAddress(true);
            addressRepository.save(existingAddress);

        } else {

            Address userDefaultAddress = addressService.getUserDefaultAddress(loggedInUser());
            userDefaultAddress.setIsDefaultShippingAddress(false);
            addressService.saveAddress(userDefaultAddress, loggedInUser());

            currentAddress.setIsDefaultShippingAddress(true);
            addressService.saveAddress(currentAddress, user);
        }

    }

    private OrderResponseDTO buildOrderResponse(Order order, OrderRequestDTO orderRequest){
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setCardDiscount(order.getCardDiscount());
        responseDTO.setDeliveryFee(order.getDeliveryFee());
        responseDTO.setDeliveryMethod(order.getDeliveryMethod());
        responseDTO.setShippingAddress(order.getShippingAddress());

        List<ProductItem> products =  getCartItems(orderRequest).stream()
                .map(cartItem -> modelMapper.map(cartItem.getProduct(), ProductItem.class))
                .collect(Collectors.toList());

        responseDTO.setProducts(products);

        return responseDTO;
    }

    private Order buildOrder(OrderRequestDTO request){
        Order order = new Order();
        order.setTotalCost(request.getTotalCost());
        order.setPaymentType(request.getPaymentType());
        order.setIsGift(request.getIsGift());
        order.setDeliveryMethod(request.getDeliveryMethod());
        order.setDeliveryFee(request.getDeliveryFee());

        order.setShippingAddress(modelMapper.map(request.getShippingAddress(), ShippingAddress.class));
        order.setDateDelivered(null);
        order.setCardDiscount(request.getCardDiscount());

        order.setUser(loggedInUser());
        order.setCartItems(getCartItems(request));


        return order;
    }
}




