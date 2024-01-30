package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.User;
import com.roima.exammanagement.repository.UserRepository;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

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





}
