package com.upc.finances.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "costs")
@Data
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CostType costType;
    private String motive;
    @Embedded
    private ExpressedValue expressedValue;
    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

}
