package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.UserMcqQuestionSubmission;
import com.roima.exammanagement.rest.v1.dto.UserMcqQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserMcqQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleUserMcqQuestionSubmissionMapper extends BaseMapper<UserMcqQuestionSubmission, SimpleUserMcqQuestionSubmissionDTO> {
}
