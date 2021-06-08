package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.CartItem;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.Response;

public interface CartItemService {
    Response addItemToCart(User user, Long productId);
    CartItem saveCartItem(CartItem item);

}
