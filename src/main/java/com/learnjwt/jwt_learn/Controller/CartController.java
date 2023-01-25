package com.learnjwt.jwt_learn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learnjwt.jwt_learn.Entity.Cart;
import com.learnjwt.jwt_learn.Service.CartService;

@RestController
public class CartController {
    
    @Autowired
    private CartService cartService;

    @GetMapping({"/addtoCar/{productId}"})
    @PreAuthorize("hasRole('User')")
    public Cart addtoCart(@PathVariable(name = "productId") Long productId){
        return cartService.addtoCart(productId);
    }
}
