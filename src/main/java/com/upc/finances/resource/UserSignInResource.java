package com.upc.finances.resource;

import lombok.Data;

@Data
public class UserSignInResource {
    private String email;
    private String password;
}
