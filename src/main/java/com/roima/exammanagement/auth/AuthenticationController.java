package com.roima.exammanagement.auth;


import com.nimbusds.jose.shaded.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
       try{
           return new ResponseEntity<AuthenticationResponse>(authenticationService.register(registerRequest), HttpStatus.CREATED);
       }catch (DataIntegrityViolationException e){
           return new ResponseEntity<>(AuthenticationResponse.builder().error("Duplicate Entry").build(),HttpStatus.BAD_REQUEST);
       }catch (HttpMessageNotReadableException e){
           return new ResponseEntity<>(AuthenticationResponse.builder().error("Invalid Data").build(),HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return new ResponseEntity<>(authenticationService.authenticate(authenticationRequest),HttpStatus.OK);
    }
}
