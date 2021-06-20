package com.upc.finances.domain.model;

import lombok.Getter;
import lombok.Value;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
@Getter
public class ExpressedValue implements Serializable {
    public ExpressedValue(){

    }
    private ValueType valueType;
    private BigDecimal value;
}
