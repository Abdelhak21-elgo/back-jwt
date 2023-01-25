package com.learnjwt.jwt_learn.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjwt.jwt_learn.Configuration.JwtRequestFilter;
import com.learnjwt.jwt_learn.Dao.CartDao;
import com.learnjwt.jwt_learn.Dao.ProductDao;
import com.learnjwt.jwt_learn.Dao.UserDao;
import com.learnjwt.jwt_learn.Entity.Cart;
import com.learnjwt.jwt_learn.Entity.Product;
import com.learnjwt.jwt_learn.Entity.User;

@Service
public class CartService {
    
    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addtoCart(Long productId){

        Product product = productDao.findById(productId).get();

        String currentUser = JwtRequestFilter.CURENT_USER;
        User user = null;

        if(product != null){
            user = userDao.findById(currentUser).get();
        }

        if(product != null && user != null){
          Cart cart = new Cart(product,user);

          return cartDao.save(cart);
        }
        return null;
    }
}
