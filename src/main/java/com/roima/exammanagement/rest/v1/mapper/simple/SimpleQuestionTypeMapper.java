package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.QuestionType;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionTypeDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SimpleQuestionTypeMapper extends BaseMapper<QuestionType, SimpleQuestionTypeDTO> {
}
