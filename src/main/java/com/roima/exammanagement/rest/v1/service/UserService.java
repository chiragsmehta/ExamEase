package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.UserRepository;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExamRepository examRepository;


    public UserDTO getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElseThrow();
        return userMapper.toDTO(user);
    }

    public UserDTO findUserById(Long id){
        User user =  userRepository.findById(id).orElse(null);
        if(user != null){
            return userMapper.toDTO(user);
        }else{
            throw  new UsernameNotFoundException("no user found with id " + id);
        }
    }

    public UserDTO findUserByName(String name){
        User user =  userRepository.findByName(name).orElse(null);
        if(user != null){
            return userMapper.toDTO(user);
        }else{
            throw  new UsernameNotFoundException("no user found with name" + name);
        }
    }
    public UserDTO findUserByEmail(String email){
        User user =  userRepository.findByEmail(email).orElse(null);
        if(user != null){
            return userMapper.toDTO(user);
        }else{
            throw  new UsernameNotFoundException("no user found with email" + email);
        }
    }

    public List<UserDTO> findAll(){
        return  userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    public List<UserDTO> findByExamId(Long examId) {
        List<User> users = userRepository.findAllByExamId(examId);
        return users.stream().map(userMapper::toDTO).toList();
    }

    public Boolean deleteUserById(Long userId){
        try {
            userRepository.deleteById(userId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
