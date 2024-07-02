package com.springsec.sec3.service;

import com.google.gson.Gson;
import com.springsec.sec3.dto.OrderRequest;
import com.springsec.sec3.dto.OrderResponse;
import com.springsec.sec3.entity.Orders;
import com.springsec.sec3.redis.RedisPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RedisPublisher redisPublisher;

    public int createOrder(OrderRequest orderRequest) {

        Orders orders = Orders.builder().item(orderRequest.getItem()).qty(orderRequest.getQnty())
                .username(orderRequest.getUsername())
                .status(Status.CONFIRM.name())
                .build();

        if (ObjectUtils.isEmpty(orderRequest.getUsername())) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            orders.setUsername(principal.getUsername());
        }
        orders = orderRepository.save(orders);
        String message = new Gson().toJson(orders);
        redisPublisher.publish(message);
        return orders.getId();
    }

    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(v -> orderMapper(v)).sorted(Comparator.comparing(OrderResponse::getUsername))
                .collect(Collectors.toList());
    }

    private OrderResponse orderMapper(Orders v) {
        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(v.getId())
                .item(v.getItem())
                .qnty(v.getQty())
                .username(v.getUsername()).build();
        return orderResponse;

    }

    public OrderResponse updateOrder(OrderRequest orderRequest) {
        Optional<Orders> byId = orderRepository.findById(orderRequest.getOrderId());
        if (byId.isPresent()) {
            Orders orders = byId.get();
            orders.setStatus(orderRequest.getStatus());
            orders.setComment(orderRequest.getComment());
            orders = orderRepository.save(orders);
            OrderResponse orderResponse = getOrderResponse(orders);
            return orderResponse;
        } else
            return null;
    }

    private OrderResponse getOrderResponse(Orders orders) {
        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(orders.getId())
                .qnty(orders.getQty())
                .status(orders.getStatus())
                .comment(orders.getComment())
                .username(orders.getUsername())
                .item(orders.getItem())
                .build();
        return orderResponse;
    }
}
