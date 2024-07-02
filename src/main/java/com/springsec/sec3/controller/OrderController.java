package com.springsec.sec3.controller;

import com.springsec.sec3.dto.OrderRequest;
import com.springsec.sec3.dto.OrderResponse;

import com.springsec.sec3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping(value = "/orders")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {

        int orderId = orderService.createOrder(orderRequest);

        OrderResponse orderResponse = OrderResponse.builder().orderId(orderId).build();

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(value = "/orders")
    public List<OrderResponse> getOrders() {

        return orderService.getOrders();
    }

    @PutMapping(value = "/orders")
    public OrderResponse updateOrders(@RequestBody OrderRequest orderRequest) {

        return orderService.updateOrder(orderRequest);
    }


}
