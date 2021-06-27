package com.upc.finances.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "costs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CostType costType;
    private String motive;
    @Embedded
    private ExpressedValue expressedValue;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Invoice invoice;
}
