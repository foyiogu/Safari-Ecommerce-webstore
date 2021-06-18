package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.security.service.UserDetailsImpl;

public interface FavouriteService {
    String customerAddProductToFavorite(UserDetailsImpl userImpl, Long productId);
}
