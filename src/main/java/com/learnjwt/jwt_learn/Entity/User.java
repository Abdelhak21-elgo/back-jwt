package com.learnjwt.jwt_learn.Entity;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private String userName;
    private String usrFirstName;
    private String userLastName;
    private String userPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", 
        joinColumns = {
            @JoinColumn(name = "USER_ID")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
        }    
    )
    private Set<Role> role;
}
