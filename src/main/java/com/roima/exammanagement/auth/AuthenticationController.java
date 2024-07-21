package com.roima.exammanagement.auth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
       try{
           AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);
           log.info("User registered with email: " + registerRequest.getEmail());
           return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.CREATED);
       }catch (DataIntegrityViolationException e){
           log.info("Duplicate register request for email: " + registerRequest.getEmail());
           return new ResponseEntity<>(AuthenticationResponse.builder().error("Duplicate Entry").build(),HttpStatus.BAD_REQUEST);
       }catch (HttpMessageNotReadableException e){
           return new ResponseEntity<>(AuthenticationResponse.builder().error("Invalid Data").build(),HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ){

       try{
           AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
           log.info("Valid User authentication(login) request  for email: " + authenticationRequest.getEmail());
           return new ResponseEntity<>(authenticationResponse,HttpStatus.OK);
       }catch (Exception e){
           log.info("Bad authentication(login) request for email: " + authenticationRequest.getEmail());
           return  new ResponseEntity<>(AuthenticationResponse.builder().error("Not valid data").build(),HttpStatus.BAD_REQUEST);
       }
    }
}
