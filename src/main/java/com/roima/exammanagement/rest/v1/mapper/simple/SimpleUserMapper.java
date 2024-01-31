package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.User;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SimpleUserMapper extends BaseMapper<User, SimpleUserDTO> {

}
