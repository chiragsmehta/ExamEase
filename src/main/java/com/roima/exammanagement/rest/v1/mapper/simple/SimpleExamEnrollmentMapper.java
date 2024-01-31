package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.rest.v1.dto.ExamEnrollmentDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamEnrollmentDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleExamEnrollmentMapper extends BaseMapper<ExamEnrollment, SimpleExamEnrollmentDTO> {
}
