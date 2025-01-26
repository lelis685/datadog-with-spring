package com.example.datadog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends MongoRepository<CreditCardModel, String> {

}
