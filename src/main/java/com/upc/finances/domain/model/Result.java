package com.upc.finances.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Double annualEffectiveRate;
    private short daysPassed;
    private Double effectiveRate;
    private Double discountedRate;
    private BigDecimal discount;
    private BigDecimal totalInitialCosts;
    private BigDecimal netValue;
    private BigDecimal totalValueToReceive;
    private BigDecimal totalFinalCosts;
    private BigDecimal totalValueToDeliver;
    private Double annualEffectiveCostRate;
}
