package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.model.OrderRequestDTO;
import com.decagon.safariwebstore.service.serviceImplementation.CheckOutServiceImplementation;
import com.decagon.safariwebstore.service.serviceImplementation.OrderServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutController {
    private CheckOutServiceImplementation service;

    @PostMapping("/check-out")
    public ResponseEntity<?> doCheckOut(@RequestBody OrderRequestDTO orderRequest){
        return null;
    }
}
