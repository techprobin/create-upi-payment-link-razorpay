# Create UPI Payment Link . full solution Code and dependecies all bundled. 

## Requirements

Razorpay integrated 'Create a UPI Payment Link' API

Key Implementations:
1. an API with path '/initiatePayment' in PaymentController which will take request in form of InitiatePaymentRequestDto and return PaymentLink (String)

2. PaymentService method 'String initiatePayment(String name, String phoneNumber, String email, Double amount,String description)'.

3. initiatePayment method in RazorpayPaymentGatewayClient taking help from Razorpay Official Documentation and Razorpay Client bean created in RazorpayConfig.


## Hints

 - All requireed already in pom.xml or application.properties. Dependency is already added and properties are already set.
 - Testcases set up.
 - You can refer to [link](https://razorpay.com/docs/api/payments/payment-links/create-upi/) for help.
