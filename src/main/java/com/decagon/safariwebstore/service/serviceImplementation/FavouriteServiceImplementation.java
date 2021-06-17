package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.model.Favourite;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.repository.FavouriteRepository;
import com.decagon.safariwebstore.security.service.UserDetailsImpl;
import com.decagon.safariwebstore.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavouriteServiceImplementation implements FavouriteService {
    @Autowired
    FavouriteRepository favouriteRepository;

    @Autowired
    UserServiceImplementation userServiceImplementation;
    @Override
    public boolean customerAddProductToFavorite(UserDetailsImpl userImpl, Long productId) {
        User user = userServiceImplementation.findUserByEmail(userImpl.getUsername());
        Long userId = user.getId();
        Optional<Favourite> favouriteOptional = favouriteRepository.findFavouriteByUserIdAndProductId(userId,productId);
        System.out.println(favouriteOptional);
        if(favouriteOptional.isPresent()){
            favouriteRepository.deleteById(favouriteOptional.get().getId());
        }else{
            Favourite newFavorite = new Favourite();
            newFavorite.setProductId(productId);
            newFavorite.setUserId(userId);
            favouriteRepository.save(newFavorite);
        }
        return false;
    }
}
