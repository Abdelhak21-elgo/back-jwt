package com.learnjwt.jwt_learn.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjwt.jwt_learn.Entity.Product;

@Repository
public interface ProductDao extends CrudRepository<Product,Long>{
    
}
