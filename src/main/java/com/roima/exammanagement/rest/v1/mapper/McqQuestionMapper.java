package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.McqQuestion;
import com.roima.exammanagement.rest.v1.dto.McqQuestionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface McqQuestionMapper extends BaseMapper<McqQuestion, McqQuestionDTO> {
}
