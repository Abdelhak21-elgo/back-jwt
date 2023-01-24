package com.learnjwt.jwt_learn.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learnjwt.jwt_learn.Entity.User;

@Repository
public interface UserDao extends JpaRepository<User,String>{
    @Query("from User where verificationToken = :token" )
    public  User findByVerificationToken(@Param("token")String token);
}
