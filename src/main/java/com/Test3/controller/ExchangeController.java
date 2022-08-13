package com.Test3.controller;

import com.Test3.model.ConvertModelData;
import com.Test3.model.ExchangeStats;
import com.Test3.model.Query;
import com.Test3.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping("/exchange/query")
    public ConvertModelData exchange(@Valid @RequestBody Query query) {
        return exchangeService.exchange(query);
    }

    @GetMapping("/exchange/stats")
    public ExchangeStats exchageStats() {
        return exchangeService.exchangeStats();
    }

}
