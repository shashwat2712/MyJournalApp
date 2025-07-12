package com.vyas.myFirstProject.controller;

import com.vyas.myFirstProject.entity.User;
import com.vyas.myFirstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        System.out.println("Accepted");
        userService.saveNewUser(user);
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        System.out.println("hello");
         return "Ok";
    }
}
