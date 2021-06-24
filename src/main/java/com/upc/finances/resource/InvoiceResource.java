package com.upc.finances.resource;
import com.upc.finances.domain.model.Cost;
import com.upc.finances.domain.model.Rate;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class InvoiceResource {
    private Long id;
    private LocalDate issueDate;
    private LocalDate paymentDate;
    private String currency;
    private BigDecimal totalCharged;
    private BigDecimal retention;
    private Rate rate;
    List<Cost> initialCosts;
    List<Cost> finalCosts;
}
