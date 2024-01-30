package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.UserExamStatus;
import com.roima.exammanagement.rest.v1.dto.UserExamStatusDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserExamStatusMapper extends BaseMapper<UserExamStatus, UserExamStatusDTO> {
}
