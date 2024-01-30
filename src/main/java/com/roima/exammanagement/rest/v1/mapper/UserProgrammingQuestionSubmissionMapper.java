package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.UserProgrammingQuestionSubmission;
import com.roima.exammanagement.rest.v1.dto.UserProgrammingQuestionSubmissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProgrammingQuestionSubmissionMapper extends BaseMapper<UserProgrammingQuestionSubmission, UserProgrammingQuestionSubmissionDTO> {
}
