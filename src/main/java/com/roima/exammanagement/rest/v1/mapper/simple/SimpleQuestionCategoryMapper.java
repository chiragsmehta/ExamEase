package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.QuestionCategory;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionCategoryDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleQuestionCategoryMapper extends BaseMapper<QuestionCategory, SimpleQuestionCategoryDTO> {
}
