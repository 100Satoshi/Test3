package com.Test3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatesModel {

    int id;
    boolean success;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Timestamp timestamp;
    String base;
    LocalDate date;
    Map<String, Double> rates;
}
