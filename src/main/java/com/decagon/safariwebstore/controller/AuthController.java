package com.decagon.safariwebstore.controller;


import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.ERole;
import com.decagon.safariwebstore.model.Role;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.request.auth.RegisterUser;
import com.decagon.safariwebstore.payload.response.UserDTO;
import com.decagon.safariwebstore.repository.RoleRepository;
import com.decagon.safariwebstore.service.UserService;
import com.decagon.safariwebstore.utils.mailService.MailService;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final MailService mailService;

    @PostMapping("/register")
    public UserDTO register(@Valid @RequestBody RegisterUser registerUser) throws UnirestException {
        User user = userService.registration(registerUser);
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        User savedUser = userService.saveUser(user);
        if(savedUser != null){
            mailService.sendMessage(registerUser.getEmail(), "Test mail", "Registration successful");
        }

        return UserDTO.build(user);
    }

    @PostMapping("/admin/register")
    public UserDTO registerAdmin(@Valid @RequestBody RegisterUser registerUser) throws UnirestException {
        User user = userService.registration(registerUser);
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName(ERole.ADMIN)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        // send mail...
        User savedUser = userService.saveUser(user);
        if(savedUser != null){
            mailService.sendMessage(registerUser.getEmail(), "Test mail", "Registration successful");
        }

        return UserDTO.build(user);
    }

}