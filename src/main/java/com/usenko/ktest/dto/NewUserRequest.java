package com.usenko.ktest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class NewUserRequest {
    @NotBlank
    @Email
    @Length(min = 6, max = 254)
    private String email;
    @NotBlank
    @Length(min = 2, max = 254)
    private String name;
    private String password;
}
