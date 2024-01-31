package com.roima.exammanagement.rest.v1.dto.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserDTO {

    private Long id;
    private String username;
    private String email;
//    private String password;
    private Boolean isAdmin;
}
