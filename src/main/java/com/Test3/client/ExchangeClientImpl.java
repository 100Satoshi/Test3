package com.Test3.client;

import com.Test3.model.ConvertModel;
import com.Test3.model.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ExchangeClientImpl implements ExchangeClient {

    private final WebClient webClient;

    @Override
    public ConvertModel exchange(Query query) {
        return webClient
                .get()
                .uri("/convert?to={to}&from={from}&amount={amount}", query.getTo(), query.getFrom(), query.getAmount())
                .retrieve()
                .bodyToMono(ConvertModel.class)
                .block();
    }
}
