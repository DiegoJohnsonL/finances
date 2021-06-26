package com.upc.finances.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rates")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private int daysPerYear;
    private int ratePeriod;
    @Column(name = "discount_date", columnDefinition = "DATE")
    private LocalDate discountDate;
    private Double rate;
    private int capitalizationPeriod;
    @JsonIgnore
    @OneToOne(mappedBy = "rate")
    private Invoice invoice;
}
