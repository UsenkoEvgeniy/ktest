package com.usenko.ktest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewQuoteDto {
    @NotBlank
    private String content;
}
