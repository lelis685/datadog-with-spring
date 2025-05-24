package com.example.datadog.config;

import com.timgroup.statsd.StatsDClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class MetricsHelper {

    @Autowired
    private StatsDClient statsDClient;


    public void incrCounter(String metricName, String... tags) {
        statsDClient.incrementCounter(metricName, createTag(tags));
    }

    public void setGauge(String metricName, Integer value, String... tags) {
        statsDClient.recordGaugeValue(metricName, value, createTag(tags));
    }

    public void setDistribution(String metricName, Integer value, String... tags) {
        statsDClient.recordDistributionValue(metricName, value, createTag(tags));
    }


    protected String[] createTag(String... tags) {
        if (tags.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid number of tags: Expected an even count, but got " + tags.length);
        }
        List<String> output = new ArrayList<>();
        for (int i = 0; i < tags.length - 1; i += 2) {
            output.add(tags[i] + ":" + tags[i + 1]);
        }
        return output.toArray(String[]::new);
    }


}
