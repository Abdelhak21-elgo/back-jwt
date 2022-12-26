package com.learnjwt.jwt_learn.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjwt.jwt_learn.Dao.RoleDao;
import com.learnjwt.jwt_learn.Entity.Role;

@Service
public class RoleService {
    
    @Autowired
    private RoleDao roledao;

    public Role createNewRole(Role role){
        return roledao.save(role);
    }
}
