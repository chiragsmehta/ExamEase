package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.QuestionType;
import com.roima.exammanagement.rest.v1.dto.QuestionTypeDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface QuestionTypeMapper extends BaseMapper<QuestionType, QuestionTypeDTO> {
}
