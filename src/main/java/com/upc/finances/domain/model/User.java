package com.upc.finances.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int ruc;
    private int dni;
    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;
    @Embedded
    private Address address;
    private String email;
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Invoice> invoices;
}
