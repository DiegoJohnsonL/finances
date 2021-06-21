package com.upc.finances.resource;
import com.upc.finances.domain.model.Cost;
import com.upc.finances.domain.model.Period;
import com.upc.finances.domain.model.Rate;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class SaveInvoiceWithEffectiveRateResource {
    private LocalDate issueDate;
    private LocalDate paymentDate;
    private BigDecimal totalCharged;
    private BigDecimal retention;
    private Rate rate;
    private List<SaveCostResource> initialCosts;
    private List<SaveCostResource> finalCosts;
}
