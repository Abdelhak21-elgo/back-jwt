package com.learnjwt.jwt_learn.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class OderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String orderFullName;

    private String orderFullAdress;

    private String corderContactNumber;

    private String corderAlternateContactNumber;

    private String orderStatus;

    private Double orderAmount;
    @OneToOne( cascade = CascadeType.ALL)
    private Product product;
    @OneToOne( cascade = CascadeType.ALL)
    private User user;

    public OderDetail(String orderFullName, String orderFullAdress, String corderContactNumber,
            String corderAlternateContactNumber, String orderStatus, Double orderAmount, Product product, User user) {
        this.orderFullName = orderFullName;
        this.orderFullAdress = orderFullAdress;
        this.corderContactNumber = corderContactNumber;
        this.corderAlternateContactNumber = corderAlternateContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.product = product;
        this.user = user;
    }
}
