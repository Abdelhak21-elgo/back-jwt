package com.learnjwt.jwt_learn.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learnjwt.jwt_learn.Dao.ProductDao;
import com.learnjwt.jwt_learn.Entity.Product;

@Service
public class ProductService {
    
    @Autowired
    private ProductDao productDao;


    public Product addnewProduct(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProducts(int pageNumber, String searchKey){
        Pageable pageable = PageRequest.of(pageNumber,4);
        if(searchKey.equals("")){
            return (List<Product>)productDao.findAll(pageable);
        }else {
            return (List<Product>) productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }
        
    }

    public List<Product> getAllProduct(){
        return (List<Product>)productDao.findAll();
    }

    public Product getProductDetailsByid(Long productId){
        return productDao.findById(productId).get();
    }

    public void deletproductDetails(Long productId){
        productDao.deleteById(productId);
    }

    public List<Product> getProductdetails(boolean isSingleProductCheckout, Long productId){
        if(isSingleProductCheckout){
            // we are bying singl product 
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        }else{
            // we are cheking the entire cart
        }
        return new ArrayList<>();
    }
}
