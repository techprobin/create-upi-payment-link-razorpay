package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InitiatePaymentRequestDto {
    String name;
    String phoneNumber;
    String email;
    Double amount;
    String description;
}
