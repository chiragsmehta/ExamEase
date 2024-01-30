package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.UserMcqQuestionSubmission;
import com.roima.exammanagement.rest.v1.dto.UserMcqQuestionSubmissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMcqQuestionSubmissionMapper extends BaseMapper<UserMcqQuestionSubmission, UserMcqQuestionSubmissionDTO> {
}
