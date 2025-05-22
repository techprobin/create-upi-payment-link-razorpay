package org.example.evaluations.evaluation.clients;

import com.razorpay.PaymentLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Component
public class RazorpayPaymentGatewayClient {

    @Autowired
    private RazorpayClient razorpayClient;

    public String initiatePayment(String name, String phoneNumber, String email,Double amount,String description) {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("upi_link", true);
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", false);
            paymentLinkRequest.put("first_min_partial_amount", 100);
            paymentLinkRequest.put("description", description);
            JSONObject customer = new JSONObject();
            customer.put("name", phoneNumber);
            customer.put("contact", name);
            customer.put("email", email);
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            JSONObject notes = new JSONObject();
            notes.put("policy_name", "Jeevan Bima");
            paymentLinkRequest.put("notes", notes);
            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
            return paymentLink.get("short_url").toString();
        } catch (RazorpayException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
