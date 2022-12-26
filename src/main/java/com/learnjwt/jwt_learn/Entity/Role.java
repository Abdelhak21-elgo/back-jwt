package com.learnjwt.jwt_learn.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Role {
    @Id
    private String rolename;
    private String roleDescription;

}
