package com.Test3.service;

import com.Test3.client.ExchangeClient;
import com.Test3.model.*;
import com.Test3.repository.ConvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private final ExchangeClient exchangeClient;

    private final RatesModel ratesModel;

    public ConvertModelData exchange(Query query) {
        ConvertModel convertModel = exchangeClient.exchange(query);
        return convertRepository.save(ConvertModelData.fromConvertModel(convertModel, ratesModel));
    }

    public ExchangeStats exchangeStats() {
        List<ConvertModelData> all = convertRepository.findAll();
        return ExchangeStats.builder()
                .requestsFromLastHour(convertRepository.findAllByTimestampAfter
                        (Timestamp.valueOf(LocalDateTime.now().minusHours(1))).size())
                .mostPopularCurrencyFrom(String.valueOf(convertRepository.findTopGroupingByFrom().toString()))
                .largestTransactionRateInEURO(convertRepository.findFirstOrderByValInBaseCurrency())
                .build();
    }

    public int requestFromLastHour() {
        return convertRepository.findAllByTimestampAfter(Timestamp.valueOf(LocalDateTime.now().minusHours(1))).size();
    }

    public String mostPopularCurrencyFrom(List<ConvertModelData> all) {
        return all.stream()
                .map(data -> data.getFrom())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(IllegalArgumentException::new)
                .getKey();
    }

    public Double largestTransaction(List<ConvertModelData> all) {
        return all.stream()
                .mapToDouble(data -> data.getValInBaseCurrency())
                .max()
                .orElseThrow(() -> new IllegalArgumentException());
    }

}
