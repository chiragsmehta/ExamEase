package com.roima.exammanagement.api.v1.controller.admin;


import com.roima.exammanagement.api.v1.dto.UserDTO;
import com.roima.exammanagement.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/admin/users")
public class UserDataController {

    @GetMapping("/all")
    public ResponseEntity<UserDTO> showAllUsers(@RequestBody UserDTO userDTO){
        // todo
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
