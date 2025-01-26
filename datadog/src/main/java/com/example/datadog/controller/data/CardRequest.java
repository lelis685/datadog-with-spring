package com.example.datadog.controller.data;


import com.example.datadog.repository.CreditCardModel;
import lombok.Data;

@Data
public class CardRequest {

    private String cardNumber;
    private String cardHolder;
    private String cardType;

    public CreditCardModel toModel() {
        return CreditCardModel.builder()
                .cardNumber(cardNumber)
                .cardHolder(cardHolder)
                .cardType(cardType)
                .build();
    }

}
