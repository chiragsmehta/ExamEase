package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.rest.v1.dto.ExamEnrollmentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface ExamEnrollmentMapper extends BaseMapper<ExamEnrollment, ExamEnrollmentDTO> {
}
