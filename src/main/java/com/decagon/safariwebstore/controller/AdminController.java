package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.ERole;
import com.decagon.safariwebstore.model.Role;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.payload.response.auth.ResetPassword;
import com.decagon.safariwebstore.repository.RoleRepository;
import com.decagon.safariwebstore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/admin/password-forgot")
    public ResponseEntity<Response> adminForgotPassword(@RequestParam("email") String email, HttpServletRequest req){
        return adminService.adminForgotPassword(req, email);
    }

    @PostMapping("/admin/password-reset")
    public ResponseEntity<Response> adminResetPassword(@Valid @RequestBody ResetPassword resetPassword) {
        return adminService.adminResetPassword(resetPassword);
    }
}

