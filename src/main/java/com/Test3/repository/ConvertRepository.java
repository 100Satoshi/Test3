package com.Test3.repository;

import com.Test3.model.ConvertModelData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvertRepository extends JpaRepository<ConvertModelData, Integer> {
}
