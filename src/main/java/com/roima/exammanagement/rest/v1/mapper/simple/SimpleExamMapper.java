package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleExamMapper extends BaseMapper<Exam, SimpleExamDTO> {
}
