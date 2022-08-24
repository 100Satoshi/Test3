package com.Test3.controller;

import com.Test3.model.ConvertModelData;
import com.Test3.model.ExchangeStats;
import com.Test3.model.Query;
import com.Test3.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping("/query")
    public ConvertModelData exchange(@Valid @RequestBody Query query) {
        return exchangeService.exchange(query);
    }

    @GetMapping("/stats")
    public ExchangeStats exchageStats() {
        return exchangeService.exchangeStats();
    }

}
