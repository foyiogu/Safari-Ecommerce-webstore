package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.dto.CartItemDTO;
import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.CartItem;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.repository.CartItemRepository;
import com.decagon.safariwebstore.service.CartItemService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImplementation implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response addItemToCart(User user, CartItemDTO cartItemDTO) {
        Response response = new Response();

        if (cartItemRepository.existsByUserAndProductId(user, cartItemDTO.getProductId())) {
            CartItem cartItem = cartItemRepository
                    .findByUserAndProductId(user, cartItemDTO.getProductId()).orElseThrow(
                            () -> {
                                throw new ResourceNotFoundException("Cart not found!");
                            }
                    );
            cartItem.setUser(user);
            cartItem.setPrice(cartItem.getPrice() + cartItemDTO.getPrice());
            int quantity = Integer.parseInt(cartItem.getQuantity());
            cartItem.setQuantity(String.valueOf(quantity + 1));

            cartItemRepository.save(cartItem);
            response.setStatus(201);
            response.setMessage("You have added another quantity of the item to cart");

        } else {
            CartItem newCart = modelMapper.map(cartItemDTO, CartItem.class);
            newCart.setUser(user);
            cartItemRepository.save(newCart);

            response.setStatus(201);
            response.setMessage("You have added a new item to cart");
        }
        return response;
    }


    @Override
    public CartItem saveCartItem(CartItem item) {
        return cartItemRepository.save(item);
    }
}
