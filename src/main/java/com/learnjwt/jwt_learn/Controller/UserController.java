package com.learnjwt.jwt_learn.Controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.learnjwt.jwt_learn.Dao.UserDao;
import com.learnjwt.jwt_learn.Entity.User;
import com.learnjwt.jwt_learn.Service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userservice;

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void initRolesAndUsers(){
        userservice.initRoleandUser();
    }
    
    @PostMapping({"/regestNewUser"})
    public User regestNewUser(@RequestBody User user){
        return userservice.registerNewUser(user);
    }
    // verifail email
    @GetMapping({"/verify"})
    public String verifyAccount(@RequestParam String token) {
        User user = userDao.findByVerificationToken(token);
        if (user != null) {
            user.setEnable(true);
            userDao.save(user);
            return "Account verified successfully";
        } else {
            return "Invalid verification token";
        }
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
