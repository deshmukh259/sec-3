package com.springsec.sec3.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private int orderId;
    private String item;
    private int qnty;
    private String username;
}
