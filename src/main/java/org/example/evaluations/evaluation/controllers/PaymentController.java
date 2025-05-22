package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.InitiatePaymentRequestDto;
import org.example.evaluations.evaluation.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/initiatePayment")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto) {
        return paymentService.initiatePayment(initiatePaymentRequestDto.getName(),initiatePaymentRequestDto.getPhoneNumber(),initiatePaymentRequestDto.getEmail(),initiatePaymentRequestDto.getAmount(),initiatePaymentRequestDto.getDescription());
    }
}
