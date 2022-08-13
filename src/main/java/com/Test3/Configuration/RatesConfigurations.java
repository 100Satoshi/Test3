package com.Test3.Configuration;

import com.Test3.model.RatesModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RatesConfigurations {

    @Bean
    public RatesModel ratesFromApi() {
        RatesModel ratesModel = WebClient.create()
                .get()
                .uri("https://api.apilayer.com/exchangerates_data/latest")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("apikey", "2rz2UiKOW48tq3BTVOo7kUomIqLHZ1uV")
                .retrieve()
                .bodyToMono(RatesModel.class)
                .block();
        return ratesModel;
    }
}
