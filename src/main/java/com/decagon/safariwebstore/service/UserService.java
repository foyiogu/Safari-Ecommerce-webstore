package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.request.auth.RegisterUser;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.payload.response.auth.ResetPassword;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public interface UserService {
    User saveUser(User user);
    boolean existsByMail(String email);
    User registration(RegisterUser registerUser);
    Optional<User> findUserByResetToken(String resetToken);
    Optional<User> getUserByEmail(String email);
    void deactivateResetPasswordToken();
    User findUserByEmail(String email);
    ResponseEntity<Response> userForgotPassword(HttpServletRequest request, String accountEmail);
    ResponseEntity<Response> userResetPassword(ResetPassword requestPassword);


}
