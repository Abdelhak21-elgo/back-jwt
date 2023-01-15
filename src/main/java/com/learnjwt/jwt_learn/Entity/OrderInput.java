package com.learnjwt.jwt_learn.Entity;

import java.util.ArrayList;
import java.util.List;



import lombok.Data;



@Data
public class OrderInput {

    private String fullName;
    private String fullAdress;
    private String contactNumber;
    private String contactAlterNumber;
    private List<OrderProductQuantity> orderProductQuantityList = new ArrayList<>();

}
