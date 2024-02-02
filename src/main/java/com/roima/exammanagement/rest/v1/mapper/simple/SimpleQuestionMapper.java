package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.Question;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleQuestionMapper extends BaseMapper<Question, SimpleQuestionDTO> {
}
