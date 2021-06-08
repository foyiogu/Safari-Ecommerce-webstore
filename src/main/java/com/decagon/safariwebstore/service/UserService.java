package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.request.UpdatePasswordRequest;
import com.decagon.safariwebstore.payload.request.auth.LoginRequest;
import com.decagon.safariwebstore.payload.request.auth.RegisterUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User saveUser(User user);
    boolean existsByMail(String email);
    User registration(RegisterUser registerUser);
    User findUserByEmail(String email);
   boolean checkIfValidOldPassword(User user, UpdatePasswordRequest updatePasswordRequest);
    boolean changeUserPassword(User user, UpdatePasswordRequest updatePasswordRequest);
//    void updatePassword(UpdatePasswordRequest updatePasswordRequest, User user);
}
