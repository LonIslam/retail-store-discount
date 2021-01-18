package com.example.retailstorediscounts.controller;

import com.example.retailstorediscounts.model.UserModel;
import com.example.retailstorediscounts.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        userModel = usersService.createUser(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }
}
