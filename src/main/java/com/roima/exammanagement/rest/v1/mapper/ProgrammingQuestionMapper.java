package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.ProgrammingQuestion;
import com.roima.exammanagement.rest.v1.dto.ProgrammingQuestionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgrammingQuestionMapper extends BaseMapper<ProgrammingQuestion, ProgrammingQuestionDTO> {
}
