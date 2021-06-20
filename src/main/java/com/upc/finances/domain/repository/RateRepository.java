package com.upc.finances.domain.repository;

import com.upc.finances.domain.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
}
