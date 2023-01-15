package com.learnjwt.jwt_learn.Controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learnjwt.jwt_learn.Entity.ImageModel;
import com.learnjwt.jwt_learn.Entity.Product;
import com.learnjwt.jwt_learn.Service.ProductService;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = { "/addnewProduct" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("hasRole('Admin')")
    public Product addProduct(@RequestPart("product") Product product,
            @RequestPart("imagefile") MultipartFile[] file) {
        // return productService.addnewProduct(product);
        try {
            Set<ImageModel> images= uploadImage(file);
            product.setProductImages(images);
            return productService.addnewProduct(product);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            imageModels.add(imageModel);
        }

        return imageModels;
    }

    
    @GetMapping({"/getAllproducts"})
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    
    @GetMapping({"/getProductdetailsById/{productId}"})
    public Product getProductdetailsById(@PathVariable("productId")Long productId){
        return productService.getProductDetailsByid(productId);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deletProducrDetails/{productId}"})
    public void deletProductDetails(@PathVariable("productId") Long productId){
        productService.deletproductDetails(productId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable("isSingleProductCheckout") boolean isSingleProductCheckout,@PathVariable("productId") Long productId){
        return productService.getProductdetails(isSingleProductCheckout, productId);
    }
}
