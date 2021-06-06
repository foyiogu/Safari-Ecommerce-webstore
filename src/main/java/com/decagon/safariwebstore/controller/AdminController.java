package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.ERole;
import com.decagon.safariwebstore.model.Role;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.repository.RoleRepository;
import com.decagon.safariwebstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/admin/password-forgot")
    public ResponseEntity<Response> adminForgotPassword(@RequestParam("email") String accountEmail, HttpServletRequest request){

        ResponseEntity<Response> responseEntity = null;

        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));

        // Lookup user in database by e-mail
        Optional<User> userOptional = userService.getUserByEmail(accountEmail);

        //url link that user will directed to from their email
        String appUrl = request.getScheme() + "://" + request.getServerName();

        Response res = userService.adminForgotPassword(adminRole, userOptional, appUrl);

        String message = res.getMessage();

        if(message.equals("Successfully sent email")) {
            responseEntity = new ResponseEntity<>(res, HttpStatus.OK);
        }else{

            if(message.equals("You don't have access to this link"))
                responseEntity = new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);

            if(message.equals("We couldn't find an account with that e-mail address."))
                responseEntity = new ResponseEntity<>(res, HttpStatus.NOT_FOUND);

        }

        return responseEntity;
    }

    @PostMapping("/admin/password-reset")
    public ResponseEntity<Response> adminResetPassword(@RequestParam Map<String, String> requestParams) {

        ResponseEntity<Response> responseEntity = null;

        String password = requestParams.get("password");
        String confirmPassword = requestParams.get("confirmPassword");

        Optional<User> optional = userService.findUserByResetToken(requestParams.get("token"));

        Response res = userService.adminResetPassword(optional, password, confirmPassword);
        String message = res.getMessage();

        if(message.equals("You have successfully reset your password. You can now login."))
            responseEntity = new ResponseEntity<>(res, HttpStatus.CREATED);
        else{

            if(message.equals("Oops!  This is an invalid password reset link."))
                responseEntity = new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

            if(message.equals("Passwords does not match"))
                responseEntity = new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;

    }
}
