package com.learnjwt.jwt_learn.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjwt.jwt_learn.Entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role,String> {
    
}
