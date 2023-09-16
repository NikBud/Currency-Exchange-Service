package com.example.currencyexcangeservice.controllers;

import com.example.currencyexcangeservice.model.CurrencyExchange;
import com.example.currencyexcangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from,
                                                  @PathVariable("to") String to)
    {
        logger.info("retrieveExchangeValue method was called with {} to {}", from, to);
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("You have typed unknown currency(ies) !"));

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
