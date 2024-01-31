package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.McqQuestionCategory;
import com.roima.exammanagement.rest.v1.dto.McqQuestionCategoryDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleMcqQuestionCategoryDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleMcqQuestionCategoryMapper extends BaseMapper<McqQuestionCategory, SimpleMcqQuestionCategoryDTO> {
}
