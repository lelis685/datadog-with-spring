package com.example.datadog.controller;


import com.example.datadog.config.MetricsHelper;
import com.example.datadog.controller.data.CardRequest;
import com.example.datadog.controller.data.CardResponse;
import com.example.datadog.service.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService service;
    @Autowired
    private MetricsHelper metricsConfig;


    @GetMapping("/cards")
    public List<CardResponse> getCreditCards() throws InterruptedException {
        putMDC("get.credit.cards");
        metricsConfig.incrCounter("cards_requests", "endpoint", "get.credit.cards");
        Instant before = Instant.now();

        List<CardResponse> allCreditCards = service.getAllCreditCards();

        metricsConfig.setGauge("cards_size", allCreditCards.size(), "endpoint", "get.credit.cards");

        Instant after = Instant.now();
        long delta = Duration.between(before, after).toMillis();
        metricsConfig.setDistribution("cards_duration_ms", (int) delta, "endpoint", "get.credit.cards");

        return allCreditCards;
    }


    @PostMapping("/cards")
    public void saveCreditCard(@RequestBody CardRequest cardRequest) throws InterruptedException {
        putMDC("save.credit.cards");
        Instant before = Instant.now();

        metricsConfig.incrCounter("cards_requests", "endpoint", "save.credit.cards");

        service.saveCreditCard(cardRequest);

        Instant after = Instant.now();
        long delta = Duration.between(before, after).toMillis();
        metricsConfig.setDistribution("cards_duration_ms", (int) delta, "endpoint", "save.credit.cards");
    }

    private void putMDC(String endpoint) {
        if (MDC.getMDCAdapter() != null) MDC.clear();
        MDC.put("TraceId", UUID.randomUUID().toString());
        MDC.put("Endpoint", endpoint);
    }

}
