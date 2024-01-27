package com.roima.exammanagement.api.v1.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Boolean isAdmin;
    private List<String> examEnrollments;


}
