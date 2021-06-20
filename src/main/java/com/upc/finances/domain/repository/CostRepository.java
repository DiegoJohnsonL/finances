package com.upc.finances.domain.repository;

import com.upc.finances.domain.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostRepository extends JpaRepository<Cost, Long> {
}
