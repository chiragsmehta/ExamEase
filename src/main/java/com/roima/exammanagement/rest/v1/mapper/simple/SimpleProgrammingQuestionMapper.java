package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.ProgrammingQuestion;
import com.roima.exammanagement.rest.v1.dto.ProgrammingQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleProgrammingQuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleProgrammingQuestionMapper extends BaseMapper<ProgrammingQuestion, SimpleProgrammingQuestionDTO> {
}
