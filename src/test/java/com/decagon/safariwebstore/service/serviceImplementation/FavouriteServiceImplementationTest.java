package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.model.*;
import com.decagon.safariwebstore.payload.request.ProductRequest;
import com.decagon.safariwebstore.repository.FavouriteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class FavouriteServiceImplementationTest {
    @Mock
    FavouriteRepository favouriteRepository;

    @Test
    void customerAddProductToFavorite() {
        Favourite favourite = new Favourite();
        favourite.setId(2L);
        favourite.setUserId(3L);
        favourite.setProductId(1L);

        final Long productId = 1L;
        final Long userId = 3L;

        Mockito.lenient().when(favouriteRepository.findFavouriteByUserIdAndProductId(userId,productId)).thenReturn(java.util.Optional.of(favourite));
        Mockito.lenient().when(favouriteRepository.save(favourite)).thenReturn(favourite);



    }

}