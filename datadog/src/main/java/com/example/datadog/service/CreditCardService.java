package com.example.datadog.service;

import com.example.datadog.controller.data.CardRequest;
import com.example.datadog.controller.data.CardResponse;
import com.example.datadog.controller.exception.InvalidCardException;
import com.example.datadog.repository.CreditCardModel;
import com.example.datadog.repository.CreditCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import static net.logstash.logback.marker.Markers.append;

@Slf4j
@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository repository;


    public void saveCreditCard(CardRequest cardRequest) throws InterruptedException {
        log.info(append("Payload", cardRequest), "Saving credit card");
        int ms = Random.from(RandomGenerator.getDefault()).nextInt(100, 550);
        Thread.sleep(ms);

        log.info("Validating credit card");
        if (cardRequest.getCardNumber().length() < 3) {
            log.error("Invalid credit card number");
            throw new InvalidCardException("Invalid credit card number");
        }

        CreditCardModel creditCardModel = cardRequest.toModel();
        repository.save(creditCardModel);
        log.info("Credit card saved successfully");
    }

    public List<CardResponse> getAllCreditCards() throws InterruptedException {
        log.info("Getting all credit cards");
        int ms = Random.from(RandomGenerator.getDefault()).nextInt(300, 950);
        Thread.sleep(ms);

        List<CardResponse> list = repository.findAll().stream().map(CreditCardModel::toResponse).toList();
        log.info(append("TotalCards", list.size()), "All credit cards retrieved successfully");
        return list;
    }


}
