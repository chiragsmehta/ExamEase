package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.Question;
import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleOptionMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleQuestionCategoryMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleQuestionTypeMapper;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {OptionMapper.class, QuestionCategoryMapper.class, QuestionTypeMapper.class,ExamMapper.class, SimpleQuestionCategoryMapper.class, SimpleOptionMapper.class, SimpleQuestionTypeMapper.class})
public interface QuestionMapper extends BaseMapper<Question, QuestionDTO> {

}
