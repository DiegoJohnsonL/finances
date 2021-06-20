package com.upc.finances.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "invoices")
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate issueDate;
    private LocalDate paymentDate;
    private BigDecimal totalCharged;
    private BigDecimal retention;
    @OneToMany(mappedBy = "invoice")
    @MapKey(name = "costType")
    private Map<CostType,Cost> costs = new HashMap<CostType, Cost>();
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_id", referencedColumnName = "id")
    private Rate rate;
    // private Rate rate;
    // private User user;
}
