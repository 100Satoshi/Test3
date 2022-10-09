package com.Test3.repository;

import com.Test3.model.ConvertModelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ConvertRepository extends JpaRepository<ConvertModelData, Integer> {

    List<ConvertModelData> findAllByTimestampAfter(Timestamp timestamp);

    @Query (nativeQuery = true, value = "SELECT from_currency, COUNT(from_currency) AS val_occ FROM exchange.convert_model_data GROUP BY from_currency ORDER BY val_occ DESC LIMIT 1;")
    List<ConvertModelData> findTopGroupingByFrom();

    @Query("SELECT max(valInBaseCurrency) FROM ConvertModelData")
    double findFirstOrderByValInBaseCurrency();

}
