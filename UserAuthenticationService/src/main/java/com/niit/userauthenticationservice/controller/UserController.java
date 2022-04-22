package com.niit.userauthenticationservice.controller;


import com.niit.userauthenticationservice.config.Consumer;
import com.niit.userauthenticationservice.domain.User;
import com.niit.userauthenticationservice.exception.InvalidCredentialsException;
import com.niit.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.userauthenticationservice.security.SecurityTokenGenerator;
import com.niit.userauthenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    private Consumer consumer;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
//    @PostMapping("/user")
//    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
//        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
//    }
//    @PostMapping("/user")
//    public ResponseEntity<?> saveUser() throws UserAlreadyExistsException {
//        User user = consumer.
//         return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
//    }

//    @PostMapping("/consume")
//    public ResponseEntity<?> viewUserFromRabbitMq(){
//        return new ResponseEntity<>(consumer.getUserDtoFromRabbitMq());
//    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException
    {
        User retrievedUser = userService.findByEmailAndPassword(user.getEmail(),user.getPassword());

        if(retrievedUser==null)
        {
            throw new InvalidCredentialsException();
        }
        Map<String,String> map = securityTokenGenerator.generateToken(user);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


}
