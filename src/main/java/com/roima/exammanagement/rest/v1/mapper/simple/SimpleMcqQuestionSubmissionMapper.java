package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.UserQuestionSubmission;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleMcqQuestionSubmissionMapper extends BaseMapper<UserQuestionSubmission, SimpleUserQuestionSubmissionDTO> {
}
