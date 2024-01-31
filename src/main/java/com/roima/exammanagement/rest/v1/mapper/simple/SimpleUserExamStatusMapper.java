package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.UserExamStatus;
import com.roima.exammanagement.rest.v1.dto.UserExamStatusDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserExamStatusDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleUserExamStatusMapper extends BaseMapper<UserExamStatus, SimpleUserExamStatusDTO> {
}
