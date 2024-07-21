package com.roima.exammanagement.rest.v1.dto.simple;

import com.roima.exammanagement.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserDTO {

    private Long id;
    private String name;
    private String email;
    private Boolean isActive;
//    private String password;
private Role role;
}
