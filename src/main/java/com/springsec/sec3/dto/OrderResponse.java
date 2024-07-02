package com.springsec.sec3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    private int orderId;
    private String item;
    private Integer qnty;
    private String username;
    private String status;
    private String comment;
}
