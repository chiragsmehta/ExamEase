package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.*;
import com.roima.exammanagement.repository.*;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.mapper.UserExamStatusMapper;
import com.roima.exammanagement.rest.v1.mapper.UserMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleExamMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleUserMapper;
import com.roima.exammanagement.rest.v1.service.utilitymodels.UserExamSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.event.LoggingEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExamRepository examRepository;
    private final PasswordEncoder passwordEncoder;


    public String generateRandomSpecialCharacters(int length){
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('A','z')
                .build();
        return pwdGenerator.generate(length);
    }

    public String createUserWithRandomPassword(UserDTO userDTO){
//        System.out.println(userDTO);
        User user = userMapper.toEntity(userDTO);
        String password = generateRandomSpecialCharacters(12);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        try{
            user.setCreatedBy(userMapper.toEntity(getCurrentUser()));
            user.setUpdatedBy(userMapper.toEntity(getCurrentUser()));
        }catch (Exception ignored){

        }

        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return password;
    }

    public UserDTO getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElse(null);
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
        return  userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public List<UserDTO> findByExamId(Long examId) {
        List<User> users = userRepository.findAllByExamId(examId);
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public Boolean deleteUserById(Long userId){
        try {
            userRepository.deleteById(userId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UserDTO update(UserDTO userDTO){
        if(userRepository.existsById(userDTO.getId())){
            User user = userRepository.findById(userDTO.getId()).orElse(null);
            userMapper.updateSourceFromTarget(userDTO,user);
            userRepository.save(user);
            return userMapper.toDTO(user);
        }else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public Boolean changePassword(String password,Long userId){
        System.out.println(password);
        if(userRepository.existsById(userId)){
            User user = userRepository.findById(userId).orElse(null);
            user.setPassword(passwordEncoder.encode(password));
            System.out.println(passwordEncoder.encode(password));
            userRepository.save(user);
            return true;
        }else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }



}
