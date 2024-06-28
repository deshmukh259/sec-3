package com.springsec.sec3.service;

import com.springsec.sec3.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public int createOrder(OrderRequest orderRequest) {

        return 1;
    }
}
