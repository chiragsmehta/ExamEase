package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.McqQuestion;
import com.roima.exammanagement.rest.v1.dto.McqQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleMcqQuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleMcqQuestionMapper extends BaseMapper<McqQuestion, SimpleMcqQuestionDTO> {
}
