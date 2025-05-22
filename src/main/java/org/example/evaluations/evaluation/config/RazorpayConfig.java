package org.example.evaluations.evaluation.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {

    @Value("${razorpay.id}")
    private String razorpayKeyId;

    @Value("${razorpay.secret}")
    private String razorpayKeySecret;

    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorpayKeyId,razorpayKeySecret);
    }
}
