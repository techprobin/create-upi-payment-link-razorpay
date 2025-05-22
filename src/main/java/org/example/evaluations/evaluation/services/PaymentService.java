package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.clients.RazorpayPaymentGatewayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    @Override
    public String initiatePayment(String name, String phoneNumber, String email, Double amount,String description) {
        return razorpayPaymentGatewayClient.initiatePayment(name,phoneNumber,email,amount,description);
    }
}
