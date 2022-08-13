package com.Test3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConvertModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean success;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="query_id")
    private Query query;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="exchange_info_id")
    private ExchangeInfo info;
    private Timestamp timestamp;
    private double result;

}
