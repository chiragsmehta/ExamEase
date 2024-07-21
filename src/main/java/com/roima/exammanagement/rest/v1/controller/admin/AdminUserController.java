package com.roima.exammanagement.rest.v1.controller.admin;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.roima.exammanagement.repository.UserRepository;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminUserController {

    private final UserService userService;



    @PostMapping("")
    public ResponseEntity<JSONObject> createUser(@RequestBody UserDTO userDTO){
        JSONObject password = new JSONObject();
        String passwordString = userService.createUserWithRandomPassword(userDTO);
        password.put("password",passwordString);
        return  new ResponseEntity<>(password,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity <UserDTO> findUserById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userService.findUserById(id),HttpStatus.OK);
        }catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO){
        try{
            return ResponseEntity.ok(userService.update(userDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{user_id}/change-password")
    public ResponseEntity<Boolean> changePassword(@PathVariable("user_id") Long userId,@RequestBody JsonNode data){
        try{
//            System.out.println(data.toString());
            String password = data.get("password").textValue();
//            System.out.println(password);
            return ResponseEntity.ok(userService.changePassword(password,userId));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
