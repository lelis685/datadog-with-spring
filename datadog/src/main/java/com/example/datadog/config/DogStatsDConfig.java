package com.example.datadog.config;

import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DogStatsDConfig {

    private final DogStatsDProperties dogStatsDProperties;

    @Bean
    public StatsDClient dogStatsDClient() {
        var client = new NonBlockingStatsDClientBuilder()
                .hostname("localhost")
                .port(8125)
                .prefix("v3.cards")
                .constantTags(dogStatsDProperties.getConstantTags());
        return client.build();
    }
}