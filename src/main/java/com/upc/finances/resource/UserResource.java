package com.upc.finances.resource;

import com.upc.finances.domain.model.Address;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserResource {
    private Long id;
    private String firstName;
    private String lastName;
    private int ruc;
    private int dni;
    private LocalDate birthDate;
    private Address address;
    private String email;
}
