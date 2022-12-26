package com.learnjwt.jwt_learn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnjwt.jwt_learn.Entity.JwtRequest;
import com.learnjwt.jwt_learn.Entity.JwtResponce;
import com.learnjwt.jwt_learn.Service.JwtService;

@RestController
@CrossOrigin
public class JwtController {
    
    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponce createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtService.createJwtToken(jwtRequest);
    }
}
