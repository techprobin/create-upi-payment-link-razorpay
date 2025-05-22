package org.example.evaluations.clients;

import com.razorpay.*;
import org.example.evaluations.evaluation.clients.RazorpayPaymentGatewayClient;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RazorpayPaymentGatewayClientUnitTest {
    @MockBean
    private RazorpayClient razorpayClient;

    @MockBean
    private PaymentLinkClient paymentLinkClient;

    @Autowired
    private RazorpayPaymentGatewayClient paymentGatewayClient;

    @BeforeEach
    public void setup() {
        razorpayClient.paymentLink = paymentLinkClient;
    }

    @Test
    void testInitiatePayment() throws RazorpayException {
        // Arrange
        String expectedUrl = "http://short.url";
        JSONObject paymentLinkResponse = new JSONObject();
        paymentLinkResponse.put("short_url",expectedUrl);

        PaymentLink paymentLink = new PaymentLink(paymentLinkResponse);
        when(paymentLinkClient.create(argThat(new JSONObjectMatcher("Payment for services",500.0,"John Doe","john.doe@example.com","1234567890",true,true,true,"Jeevan Bima")))).thenReturn(paymentLink);

        // Act
        String result = paymentGatewayClient.initiatePayment(
                "John Doe",
                "1234567890",
                "john.doe@example.com",
                500.0,
                "Payment for services"
        );

        // Assert
        assertEquals(expectedUrl, result, "The returned URL should match the expected URL");
    }

    @Test
    void testInitiatePaymentThrowsException() throws RazorpayException {
        // Arrange
        when(paymentLinkClient.create(argThat(new JSONObjectMatcher("Payment for services",500.0,"John Doe","john.doe@example.com","1234567890",true,true,true,"Jeevan Bima"))))
                .thenThrow(new RazorpayException("Failed to create payment link"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            paymentGatewayClient.initiatePayment(
                    "John Doe",
                    "1234567890",
                    "john.doe@example.com",
                    500.0,
                    "Payment for services"
            );
        });

        assertEquals("Failed to create payment link", thrown.getMessage(), "Exception message should match");
    }
}
