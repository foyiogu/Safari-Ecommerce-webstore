package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.security.service.UserDetailsImpl;

public interface FavouriteService {
    boolean customerAddProductToFavorite(UserDetailsImpl userImpl, Long productId);
}
