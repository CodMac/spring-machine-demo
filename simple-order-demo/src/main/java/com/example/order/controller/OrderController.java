package com.example.order.controller;

import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{oId}")
    public String createOrder(@PathVariable("oId") String oId) throws Exception {
        orderService.createOrder(oId);

        return "订单创建， 订单Id: " + oId;
    }

    @PutMapping("/{oId}")
    public String payOrder(@PathVariable("oId") String oId) throws Exception {
        orderService.pay(oId);

        return "订单支付， 订单Id: " + oId;
    }

    @PutMapping("/{oId}")
    public String deliveryOrder(@PathVariable("oId") String oId) throws Exception {
        orderService.delivery(oId);

        return "订单发货， 订单Id: " + oId;
    }

    @PutMapping("/{oId}")
    public String receivedOrder(@PathVariable("oId") String oId) throws Exception {
        orderService.received(oId);

        return "订单收货， 订单Id: " + oId;
    }
}
