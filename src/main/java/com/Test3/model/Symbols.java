package com.Test3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Symbols {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    private boolean succes;

    @ElementCollection
    @CollectionTable(name = "symbols_mapping",
            joinColumns = {@JoinColumn(name = "symbol_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "symbol")
    @Column(name = "symbolName")
    private Map<String, String> symbols;

}
