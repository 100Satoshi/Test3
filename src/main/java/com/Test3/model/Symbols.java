package com.Test3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Symbols {

    private int id;
    private boolean success;
    private Map<String, String> symbols;

}
