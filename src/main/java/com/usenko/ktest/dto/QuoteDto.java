package com.usenko.ktest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuoteDto {
    private Long id;
    private String content;
    private LocalDateTime creationDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime modifiedDate;
    private Long authorId;
    private Integer rating;
}
