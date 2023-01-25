package com.learnjwt.jwt_learn.Dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learnjwt.jwt_learn.Entity.Product;

@Repository
public interface ProductDao extends CrudRepository<Product,Long>{

    public List<Product> findAll(Pageable pageable);

    public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(String key1,String key2,Pageable pageable);
    
}
