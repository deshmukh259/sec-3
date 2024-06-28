package com.springsec.sec3.controller;

import com.springsec.sec3.dto.OrderRequest;
import com.springsec.sec3.dto.OrderResponse;
import com.springsec.sec3.redis.RedisPublisher;
import com.springsec.sec3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisPublisher redisPublisher;

    @PostMapping(value = "/order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return null;
    }

}
