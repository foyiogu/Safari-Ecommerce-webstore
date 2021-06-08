package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.model.CartItem;
import com.decagon.safariwebstore.model.Product;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.repository.CartItemRepository;
import com.decagon.safariwebstore.repository.ProductRepository;
import com.decagon.safariwebstore.repository.UserRepository;
import com.decagon.safariwebstore.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImplementation implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Response addItemToCart(User user, Long productId) {
        Response response = new Response();

        if (user == null) {
            response.setMessage("The user is not logged in");
            response.setStatus(406);
            return response;
        }

        try {
            CartItem cartItem = cartItemRepository.findCartItemByUserEmailAndProductId(user.getEmail(), productId);

            Optional<Product> optionalProduct = productRepository.findById(productId);

            if (optionalProduct.isEmpty()) {
                response.setStatus(404);
                response.setMessage("The product is not found");
                return response;
            }

            Product product = optionalProduct.get();

            if (cartItem != null) {
                //set properties
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setPrice(product.getPrice());
                int quantity = cartItem.getQuantity();
                cartItem.setQuantity(quantity + 1);

                cartItemRepository.save(cartItem);
                response.setStatus(201);
                response.setMessage("You have added another quantity of the item to cart");

            } else {
                CartItem newCart = new CartItem();

                newCart.setUser(user);
                newCart.setProduct(product);
                newCart.setPrice(product.getPrice());
                newCart.setQuantity(1);

                cartItemRepository.save(newCart);
                response.setStatus(201);
                response.setMessage("You have added a new item to cart");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    @Override
    public CartItem saveCartItem(CartItem item) {
        return cartItemRepository.save(item);
    }
}
