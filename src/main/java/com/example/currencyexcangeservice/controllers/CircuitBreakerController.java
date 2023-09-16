package com.example.currencyexcangeservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private final Logger logger =
            LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sampleAPI", fallbackMethod = "fallbackForSampleAPI")
    //@CircuitBreaker(name = "sampleAPI", fallbackMethod = "fallbackForSampleAPI")
    @RateLimiter(name = "sampleAPI", fallbackMethod = "fallbackForSampleAPI")
    public String sampleAPI(){
        logger.info("Sample API call received");
//        ResponseEntity<String> response = new RestTemplate()
//                .getForEntity("http://localhost:8080/dummy", String.class);
//        return response.getBody();
        return "Sample API";
    }

    public String fallbackForSampleAPI(Exception ex){
        return ex.getMessage();
    }
}
