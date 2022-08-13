package com.Test3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ExchangeStats {

    int requestsFromLastHour;
    String mostPopularCurrencyFrom;
    Double largestTransactionRateInEURO;

}
