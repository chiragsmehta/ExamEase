package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.UserQuestionSubmission;
import com.roima.exammanagement.rest.v1.dto.UserQuestionSubmissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserQuestionSubmissionMapper extends BaseMapper<UserQuestionSubmission, UserQuestionSubmissionDTO> {
}
