package com.example.datadog.controller.data;

import com.example.datadog.repository.CreditCardModel;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CardResponse {

    private String cardHolder;
    private String cardType;

}
