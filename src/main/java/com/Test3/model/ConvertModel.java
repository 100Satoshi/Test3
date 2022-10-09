package com.Test3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertModel {

    private int id;
    private boolean success;
    private Query query;
    private ExchangeInfo info;
    private Timestamp timestamp;
    private double result;

}
