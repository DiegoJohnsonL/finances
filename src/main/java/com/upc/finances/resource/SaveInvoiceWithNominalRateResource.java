package com.upc.finances.resource;
import com.upc.finances.domain.model.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class SaveInvoiceWithNominalRateResource {
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
    private Period capitalizationPeriod;
    private Result result;
    private List<SaveCostResource> initialCosts;
    private List<SaveCostResource> finalCosts;
}