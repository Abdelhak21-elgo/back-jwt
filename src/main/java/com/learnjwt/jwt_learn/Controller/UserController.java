package com.learnjwt.jwt_learn.Controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.learnjwt.jwt_learn.Entity.User;
import com.learnjwt.jwt_learn.Service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userservice;

    @PostConstruct
    public void initRolesAndUsers(){
        userservice.initRoleandUser();
    }
    
    @PostMapping({"/regestNewUser"})
    public User regestNewUser(@RequestBody User user){
        return userservice.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only for Admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only for User";
    }
}
