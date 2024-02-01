package com.roima.exammanagement.rest.v1.dto;

import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamEnrollmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
//    private String password;
    private Boolean isAdmin;
    private List<SimpleExamEnrollmentDTO> examEnrollments;



}
