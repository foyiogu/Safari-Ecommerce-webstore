package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.security.service.UserDetailsImpl;
import com.decagon.safariwebstore.service.CartItemService;
import com.decagon.safariwebstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class CartItemController {

    public final CartItemService cartItemService;
    public final UserService userService;

    @PostMapping("/add-to-cart/{productId}")
    public ResponseEntity<Response> addItemsToCart(@PathVariable Long productId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findUserByEmail(userDetails.getUsername());

        Response response = cartItemService.addItemToCart(user, productId);
        Integer status = response.getStatus();
        if (status.equals(201)) return new ResponseEntity<>(response, HttpStatus.CREATED);
        else if (status.equals(404)) return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
