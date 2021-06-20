package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.security.service.UserDetailsImpl;
import com.decagon.safariwebstore.service.FavouriteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;
    @PutMapping("/favorite/{productId}")
    public ResponseEntity<?> customerAddFavoriteProduct(@PathVariable Long productId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(favouriteService.customerAddProductToFavorite(userDetails,productId), HttpStatus.OK);
    }
}
