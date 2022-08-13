package com.Test3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ConvertModelData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "to_currency")
    private String from;
    @Column(name = "from_currency")
    private String to;
    private double amount;
    private double result;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timestamp;
    @JsonIgnore
    private double valueInBaseCurrency;

    public static ConvertModelData fromConvertModel(ConvertModel convertModel, RatesModel ratesModel) {
        return ConvertModelData.builder()
                .from(convertModel.getQuery().getFrom())
                .to(convertModel.getQuery().getTo())
                .amount(convertModel.getQuery().getAmount())
                .result(convertModel.getResult())
                .timestamp(new Timestamp(convertModel.getInfo().getTimestamp() * 1000L))
                .valueInBaseCurrency(getValueInBaseCurrency(convertModel, ratesModel))
                .build();
    }

    private static double getValueInBaseCurrency(ConvertModel convertModel, RatesModel ratesModel) {
        String from = convertModel.getQuery().getFrom();
        double amount = convertModel.getQuery().getAmount();
        double rateInBaseCurrency = ratesModel.getRates().get(from);
        double valueInBaseCurrency = amount * rateInBaseCurrency;
        return valueInBaseCurrency;
    }

}
