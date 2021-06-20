package com.upc.finances.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "nominal_rates")
public class NominalRate extends Rate{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "periodType", column = @Column(name = "cp_period_type")),
            @AttributeOverride(name = "periodDays", column = @Column(name = "cp_period_days"))
    })
    private Period capitalizationPeriod;
}
