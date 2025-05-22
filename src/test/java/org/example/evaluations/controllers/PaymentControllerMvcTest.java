package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.PaymentController;
import org.example.evaluations.evaluation.dtos.InitiatePaymentRequestDto;
import org.example.evaluations.evaluation.services.IPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
public class PaymentControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPaymentService paymentService;

    @Test
    void testInitiatePayment() throws Exception {
        // Arrange
        InitiatePaymentRequestDto requestDto = new InitiatePaymentRequestDto();
        requestDto.setName("John Doe");
        requestDto.setPhoneNumber("1234567890");
        requestDto.setEmail("john.doe@example.com");
        requestDto.setAmount(500.0);
        requestDto.setDescription("Payment for services");

        String expectedResponse = "http://short.url";
        when(paymentService.initiatePayment(
                any(String.class),
                any(String.class),
                any(String.class),
                any(Double.class),
                any(String.class)
        )).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(post("/initiatePayment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
