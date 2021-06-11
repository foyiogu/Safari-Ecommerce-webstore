package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.Role;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.payload.response.auth.ResetPassword;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    ResponseEntity<Response> adminForgotPassword(HttpServletRequest req, Role adminRole, String email);
    ResponseEntity<Response> adminResetPassword(ResetPassword resetPassword);
}
