package com.roima.exammanagement.rest.v1.dto.simple;


import lombok.Data;

import java.util.List;

@Data
public class SimplePictureDTO {
    private Long id;
    private String url;
    private String altText;
}
