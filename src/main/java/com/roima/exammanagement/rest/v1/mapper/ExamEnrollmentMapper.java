package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.rest.v1.dto.ExamEnrollmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamEnrollmentMapper extends BaseMapper<ExamEnrollment, ExamEnrollmentDTO> {
}
