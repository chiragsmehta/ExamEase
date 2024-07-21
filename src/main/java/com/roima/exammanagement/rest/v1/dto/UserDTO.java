package com.roima.exammanagement.rest.v1.dto;

import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.model.Role;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamEnrollmentDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private SimpleUserDTO createdBy;
    private LocalDateTime createdAt;
    private SimpleUserDTO updatedBy;
    private LocalDateTime updatedAt;
//    private String password;
    private Role role;
    private Boolean isActive;
    private List<SimpleExamEnrollmentDTO> examEnrollments;



}
