package com.example.datadog.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MetricsConfig {

    @Autowired
    private MeterRegistry registry;

    @Bean
    public TimedAspect timedAspect() {
        return new TimedAspect(registry);
    }


}
