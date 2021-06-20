package com.upc.finances.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private String street;
    private String city;
    private String region;
    private String country;
    private String zipCode;
}
