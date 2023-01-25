package com.learnjwt.jwt_learn.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjwt.jwt_learn.Entity.Cart;

@Repository
public interface CartDao extends CrudRepository<Cart,Long>{
    
}
