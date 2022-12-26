package com.learnjwt.jwt_learn.Entity;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter

public class JwtResponce {
    
    private User user;
    private String jwtToken;

}
