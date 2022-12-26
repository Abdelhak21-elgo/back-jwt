package com.learnjwt.jwt_learn.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjwt.jwt_learn.Entity.User;

@Repository
public interface UserDao extends CrudRepository<User,String>{
    
}
