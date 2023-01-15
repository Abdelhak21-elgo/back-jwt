package com.learnjwt.jwt_learn.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjwt.jwt_learn.Configuration.JwtRequestFilter;
import com.learnjwt.jwt_learn.Dao.OrderDetailDoa;
import com.learnjwt.jwt_learn.Dao.ProductDao;
import com.learnjwt.jwt_learn.Dao.UserDao;
import com.learnjwt.jwt_learn.Entity.OderDetail;
import com.learnjwt.jwt_learn.Entity.OrderInput;
import com.learnjwt.jwt_learn.Entity.OrderProductQuantity;
import com.learnjwt.jwt_learn.Entity.Product;
import com.learnjwt.jwt_learn.Entity.User;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACER = "Placed";

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderDetailDoa orderDetailDoa;

    public void placeOrder(OrderInput orderInput) {
        
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o : productQuantityList) {
            Product product = productDao.findById(o.getProdcutId()).get();

            String currentUser = JwtRequestFilter.CURENT_USER;
            User user = userDao.findById(currentUser).get();

            OderDetail orderdetail = new OderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAdress(),
                    orderInput.getContactNumber(),
                    orderInput.getContactAlterNumber(),
                    ORDER_PLACER,
                    product.getProductCurentPrice() * o.getQuantity(),
                    product,
                    user);

            orderDetailDoa.save(orderdetail);
        }
    }
}
