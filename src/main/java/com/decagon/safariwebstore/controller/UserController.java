package com.decagon.safariwebstore.controller;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.payload.response.auth.ResetPassword;
import com.decagon.safariwebstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/customer/password-forgot")
    public ResponseEntity<Response> userForgotPassword(@RequestParam("email") String accountEmail, HttpServletRequest request){
        return userService.userForgotPassword(request, accountEmail);
    }
    @PostMapping("/customer/password-reset")
    public ResponseEntity<Response> customerResetPassword(@Valid @RequestBody ResetPassword resetPassword) {
        return userService.userResetPassword(resetPassword);
    }

}