package com.learnjwt.jwt_learn.Entity;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtRequest {
    
    private String uerName ;
    private String userPassword;
}
