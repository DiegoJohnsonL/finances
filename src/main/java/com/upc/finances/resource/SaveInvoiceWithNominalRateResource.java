package com.upc.finances.resource;
import com.upc.finances.domain.model.Cost;
import com.upc.finances.domain.model.CostType;
import com.upc.finances.domain.model.NominalRate;
import com.upc.finances.domain.model.Period;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class SaveInvoiceWithNominalRateResource {
    private LocalDate issueDate;
    private LocalDate paymentDate;
    private BigDecimal totalCharged;
    private BigDecimal retention;
    private NominalRate nominalRate;
    private List<SaveCostResource> initialCosts;
    private List<SaveCostResource> finalCosts;
}