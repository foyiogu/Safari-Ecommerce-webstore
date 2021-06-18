package com.decagon.safariwebstore.configuration.paystackconfiguration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

class PayStackVerifyTransactionResponseTest {
    private PayStackVerifyTransactionResponse payStackVerifyTransactionResponse = new PayStackVerifyTransactionResponse();
    private InitializeTransactionResponse response = new InitializeTransactionResponse();
    private InitializeTransactionRequest request = new InitializeTransactionRequest();
    @BeforeEach
    void setup() throws Exception {
        request.setAmount(300 * 100);
        request.setEmail("adenusidamilola5@gmail.com");
        response = InitializeTransaction.initTransaction(request);
        payStackVerifyTransactionResponse = payStackVerifyTransactionResponse.verifyTransaction(response.getData().getReference());
        System.out.println(payStackVerifyTransactionResponse.getData());
    }
    @Test
    void verifyURLWasSent(){
        assertEquals("Verification successful",payStackVerifyTransactionResponse.getMessage());
        assertNotNull(payStackVerifyTransactionResponse.getData().getTransaction_date());
    }
    @Test
    void verifyPaymentHasNotBeenMade(){
        assertEquals("abandoned",payStackVerifyTransactionResponse.getData().getStatus());
        assertEquals("The transaction was not completed", payStackVerifyTransactionResponse.getData().getGateway_response());
        assertNull(payStackVerifyTransactionResponse.getData().getPaid_at());
    }
    @Test
    void randomRefKeyShouldThrowExceptions() throws Exception {
        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PayStackVerifyTransactionResponse payStackVerifyTransactionResponse = new PayStackVerifyTransactionResponse();
                payStackVerifyTransactionResponse=payStackVerifyTransactionResponse.verifyTransaction("ysttg543sha");
                System.out.println((payStackVerifyTransactionResponse));
            }
        });
    }
}