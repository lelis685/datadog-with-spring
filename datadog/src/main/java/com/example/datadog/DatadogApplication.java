package com.example.datadog;

import com.example.datadog.config.DogStatsDProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DogStatsDProperties.class)
public class DatadogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatadogApplication.class, args);
	}

}
