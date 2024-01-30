package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.McqQuestionCategory;
import com.roima.exammanagement.rest.v1.dto.McqQuestionCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface McqQuestionCategoryMapper extends BaseMapper<McqQuestionCategory,McqQuestionCategoryDTO> {
}
