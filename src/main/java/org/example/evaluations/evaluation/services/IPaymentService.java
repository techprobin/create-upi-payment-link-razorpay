package org.example.evaluations.evaluation.services;

public interface IPaymentService {
    String initiatePayment(String name, String phoneNumber, String email,Double amount,String description);
}
