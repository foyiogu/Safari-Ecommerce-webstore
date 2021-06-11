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

@Slf4j
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/password-forgot")
    public ResponseEntity<Response> adminForgotPassword(@RequestParam("email") String email, HttpServletRequest req){
        return adminService.adminForgotPassword(req, email);
    }

    @PostMapping("/admin/password-reset")
    public ResponseEntity<Response> adminResetPassword(@Valid @RequestBody ResetPassword resetPassword) {
        return adminService.adminResetPassword(resetPassword);
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequest productRequest){

        Product product = productService.saveProduct(productRequest);

        return new ResponseEntity<>(new Response(200,
                "Product saved successfully"), HttpStatus.OK);
    }

}

