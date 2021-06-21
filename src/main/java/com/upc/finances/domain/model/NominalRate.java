package com.upc.finances.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "nominal_rates")
public class NominalRate extends Rate {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "periodType", column = @Column(name = "cp_period_type")),
            @AttributeOverride(name = "periodDays", column = @Column(name = "cp_period_days"))
    })
    private Period capitalizationPeriod;

    public Period getCapitalizationPeriod() {
        return capitalizationPeriod;
    }

    public void setCapitalizationPeriod(Period capitalizationPeriod) {
        this.capitalizationPeriod = capitalizationPeriod;
    }
}
