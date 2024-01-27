package com.roima.exammanagement.api.v1.mapper;

import com.roima.exammanagement.api.v1.dto.UserDTO;
import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
//    User userDTOToUser(UserDTO userDTO);
    default  List<String> map(List<ExamEnrollment> value){
        return  value.stream().map(examEnrollment -> examEnrollment.getExam().getName()).toList();
    }
}
