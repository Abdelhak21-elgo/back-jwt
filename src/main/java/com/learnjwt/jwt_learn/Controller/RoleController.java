package com.learnjwt.jwt_learn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnjwt.jwt_learn.Entity.Role;
import com.learnjwt.jwt_learn.Service.RoleService;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleservice;
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role){
        return roleservice.createNewRole(role);
    }
}
