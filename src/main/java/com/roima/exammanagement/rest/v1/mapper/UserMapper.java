package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.model.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User,UserDTO> {

}
