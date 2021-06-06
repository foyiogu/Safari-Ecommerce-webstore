package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.request.auth.RegisterUser;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User saveUser(User user);
    boolean existsByMail(String email);
    User registration(RegisterUser registerUser);
    User findUserByEmail(String email);
}
