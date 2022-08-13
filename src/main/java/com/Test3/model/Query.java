package com.Test3.model;

import com.Test3.validation.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "to_currency")
    @Currency(message = "UNKNOWN_CURRENCY_TO")
    private String to;
    @Column(name = "from_currency")
    @Currency(message = "UNKNOWN_CURRENCY_FROM")
    private String from;
    private double amount;

}
