package com.Test3.service;

import com.Test3.model.ConvertModelData;
import com.Test3.repository.ConvertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceTest {

    @Mock
    private ConvertRepository convertRepository;

    @InjectMocks
    private ExchangeService exchangeService;


    @Test
    void shouldReturnRequestFromLastHour() {
        //given
        List<ConvertModelData> allRequest = listOfExampleRequests();
        when(convertRepository.findAllByTimestampAfter(any(Timestamp.class))).thenReturn(allRequest);

        //when
        int requestFromLastHour = exchangeService.requestFromLastHour();

        //then
        assertEquals(allRequest.size(), requestFromLastHour);
    }

    @Test
    void shouldReturnMostPopularCurrency() {
        //given
        List<ConvertModelData> allRequests = listOfExampleRequests();

        //when
        String mostPopularCurrencyFrom = exchangeService.mostPopularCurrencyFrom(allRequests);

        //then
        assertEquals("GBP", mostPopularCurrencyFrom);
    }

    @Test
    void shouldReturnLargestTransaction() {
        //given
        List<ConvertModelData> allRequests = listOfExampleRequests();

        //when
        Double largestTransaction = exchangeService.largestTransaction(allRequests);

        //then
        assertEquals(453, largestTransaction);
    }

    private static List<ConvertModelData> listOfExampleRequests() {
        ConvertModelData request1 = ConvertModelData.builder()
                .from("USD")
                .to("PLN")
                .amount(105)
                .result(489.54)
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .valInBaseCurrency(104)
                .build();

        ConvertModelData request2 = ConvertModelData.builder()
                .from("GBP")
                .to("USD")
                .amount(100)
                .result(285.43)
                .timestamp(Timestamp.valueOf(LocalDateTime.now().minusHours(2)))
                .valInBaseCurrency(321)
                .build();

        ConvertModelData request3 = ConvertModelData.builder()
                .from("GBP")
                .to("USD")
                .amount(149.37)
                .result(943.57)
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .valInBaseCurrency(453)
                .build();
        return List.of(request1,request2,request3);
    }

}