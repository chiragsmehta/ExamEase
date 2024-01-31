package com.roima.exammanagement.rest.v1.dto.simple;


import lombok.Data;

import java.util.List;

@Data
public class SimpleOptionDTO {
    private Long id;
    private String value;
    private Boolean isCorrect;
}
