package com.Test3.service;

import com.Test3.model.*;
import com.Test3.repository.ConvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ConvertRepository convertRepository;

    private final RatesModel ratesModel;

    public ConvertModelData exchange(Query query) {
        ConvertModel convertModel = WebClient.create()
                .get()
                .uri(String.format(
                        "https://api.apilayer.com/exchangerates_data/convert?to=%s&from=%s&amount=%s",
                        query.getTo(),
                        query.getFrom(),
                        query.getAmount()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("apikey", "2rz2UiKOW48tq3BTVOo7kUomIqLHZ1uV")
                .retrieve()
                .bodyToMono(ConvertModel.class)
                .block();
        return convertRepository.save(ConvertModelData.fromConvertModel(convertModel, ratesModel));
    }

    public ExchangeStats exchangeStats() {
        return ExchangeStats.builder()
                .requestsFromLastHour(requestFromLastHour().size())
                .mostPopularCurrencyFrom(mostPopularCurrencyFrom())
                .largestTransactionRateInEURO(largestTransaction())
                .build();
    }

    public List<ConvertModelData> requestFromLastHour() {
        return convertRepository.findAll().stream()
                .filter(convertModelData -> convertModelData.getTimestamp()
                        .after(Timestamp.valueOf(LocalDateTime.now().minusHours(1))))
                .collect(Collectors.toList());
    }

    public String mostPopularCurrencyFrom() {
        return convertRepository.findAll().stream()
                .map(data -> data.getFrom())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(IllegalArgumentException::new)
                .getKey();
    }

    public Double largestTransaction() {
        return convertRepository.findAll().stream()
                .mapToDouble(data -> data.getValueInBaseCurrency())
                .max()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
