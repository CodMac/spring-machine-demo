package com.example.order.entity;

import com.example.order.config.statemachine.schema.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private String oId;
    private OrderStatus status;

    public Order(String oId) {
        this.oId = oId;
    }

}
