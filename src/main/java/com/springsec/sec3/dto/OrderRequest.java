package com.springsec.sec3.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {

    private int orderId;
    private String item;
    private int qnty;
    private String username;
    private String status;
    private String comment;

}
