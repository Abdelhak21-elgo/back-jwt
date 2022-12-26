package com.learnjwt.jwt_learn.Entity;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productDiscountedPrice;
    private Double productCurentPrice;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_IMAGES", joinColumns = {
            @JoinColumn(name = "PRODUCT_ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "IMAGES_ID")
    })
    private Set<ImageModel> productImages;
}
