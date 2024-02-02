package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.Question;
import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OptionMapper.class,ExamMapper.class})
public interface QuestionMapper extends BaseMapper<Question, QuestionDTO> {
}
