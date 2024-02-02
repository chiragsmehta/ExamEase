package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.QuestionCategory;
import com.roima.exammanagement.rest.v1.dto.QuestionCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionCategoryMapper extends BaseMapper<QuestionCategory, QuestionCategoryDTO> {
}
