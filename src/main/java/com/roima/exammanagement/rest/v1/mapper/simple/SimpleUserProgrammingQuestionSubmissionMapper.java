package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.UserProgrammingQuestionSubmission;
import com.roima.exammanagement.rest.v1.dto.UserProgrammingQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserProgrammingQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleUserProgrammingQuestionSubmissionMapper extends BaseMapper<UserProgrammingQuestionSubmission, SimpleUserProgrammingQuestionSubmissionDTO> {
}
