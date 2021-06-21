package com.upc.finances.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpressedValue implements Serializable {
    private ValueType valueType;
    private BigDecimal value;
}
