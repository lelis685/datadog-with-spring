package com.example.datadog.config;

import io.micrometer.core.instrument.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MetricsHelper {

    @Autowired
    private MeterRegistry registry;

    public void incrCounter(String metricName, String... tags) {
        Counter.builder(metricName)
                .tags(tags)
                .register(registry)
                .increment();
    }

    public void setGauge(String metricName, Integer value, String... tags) {
        Gauge.builder(metricName, () -> value)
                .tags(tags)
                .register(registry);
    }

    public void setDistribution(String metricName, Integer value, String... tags) {
        DistributionSummary.builder(metricName)
                .tags(tags)
                .publishPercentileHistogram(false)
                .publishPercentiles(0.5, 0.95, 0.99)
                .register(registry)
                .record(value);
    }


}
