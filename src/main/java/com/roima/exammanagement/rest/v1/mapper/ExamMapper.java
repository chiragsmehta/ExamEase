package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper extends BaseMapper<Exam, ExamDTO> {
}
