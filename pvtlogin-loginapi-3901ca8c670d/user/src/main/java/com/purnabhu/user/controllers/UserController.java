package com.purnabhu.user.controllers;

import com.purnabhu.user.entities.User;
import com.purnabhu.user.response.ResponseEntityObject;
import com.purnabhu.user.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    private ResponseEntityObject  createUser(@RequestBody User user){
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        if (userService.existsByUsername(user.getUserName())) {
            responseEntityObject.setResponseMessage("Error: Username is already taken!");
            return responseEntityObject;
        }

        if (userService.existsByuserEmailId(user.getUserEmailId())) {
            responseEntityObject.setResponseMessage("Error: Email is already in use!");
            return responseEntityObject;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        logger.info("User creation start....");
        User createdUser = userService.createUser(user);
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("User created successfully");
        return responseEntityObject;
    }

    @GetMapping("/getAllUsers")
    private ResponseEntity<List<User>> getAllUsers(){
        logger.info("Fetch All Users....");
        List<User> usersList = userService.getAllUser();
        return new ResponseEntity<List<User>>(usersList,HttpStatus.OK);
    }
}
