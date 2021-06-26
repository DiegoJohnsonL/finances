package com.upc.finances.resource;
import com.upc.finances.domain.model.Period;
import com.upc.finances.domain.model.Rate;
import com.upc.finances.domain.model.Result;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class SaveInvoiceWithEffectiveRateResource {
    private String title;
    private String operationType;
    private LocalDate issueDate;
    private LocalDate paymentDate;
    private String currency;
    private BigDecimal totalCharged;
    private BigDecimal retention;
    private int daysPerYear;
    private Period rateTerm;
    private LocalDate discountDate;
    private Double rate;
    private Result result;
    private List<SaveCostResource> initialCosts;
    private List<SaveCostResource> finalCosts;
}
