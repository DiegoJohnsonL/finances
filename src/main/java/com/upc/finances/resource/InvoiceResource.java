package com.upc.finances.resource;
import com.upc.finances.domain.model.Cost;
import com.upc.finances.domain.model.Rate;
import com.upc.finances.domain.model.Result;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class InvoiceResource {
    private Long id;
    private String title;
    private String operationType;
    private LocalDate issueDate;
    private LocalDate paymentDate;
    private String currency;
    private BigDecimal totalCharged;
    private BigDecimal retention;
    private Rate rate;
    private Result result;
    List<Cost> initialCosts;
    List<Cost> finalCosts;
}
