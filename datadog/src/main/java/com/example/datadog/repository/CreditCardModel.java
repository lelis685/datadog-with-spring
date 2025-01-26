package com.example.datadog.repository;

import com.example.datadog.controller.data.CardResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "credit_card")
public class CreditCardModel {

    @Id
    private String id;

    private String cardNumber;
    private String cardHolder;
    private String cardType;

    public CardResponse toResponse() {
        return CardResponse.builder()
                .cardHolder(this.getCardHolder())
                .cardType(this.getCardType())
                .build();
    }

}
