package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.model.OrderRequestDTO;
import com.decagon.safariwebstore.model.OrderResponseDTO;
import com.decagon.safariwebstore.service.CheckOutService;
import com.decagon.safariwebstore.service.serviceImplementation.CheckOutServiceImplementation;
import com.decagon.safariwebstore.service.serviceImplementation.OrderServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/check-out")
public class CheckOutController {


    private final CheckOutService checkoutService;


    @PostMapping()
    public ResponseEntity<?> doCheckOut(@RequestBody OrderRequestDTO orderRequest){
        OrderResponseDTO responseDTO = checkoutService.doCheckout(orderRequest);
        return new ResponseEntity<>( responseDTO, HttpStatus.OK);
    }
}
