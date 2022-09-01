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
        String success = orderService.createOrder(oId) ? "成功" : "失败";

        return "订单创建" + success + "， 订单Id: " + oId;
    }

    @PutMapping("/{oId}/pay")
    public String payOrder(@PathVariable("oId") String oId) throws Exception {
        String success = orderService.pay(oId) ? "成功" : "失败";

        return "订单支付" + success + "， 订单Id: " + oId;
    }

    @PutMapping("/{oId}/delivery")
    public String deliveryOrder(@PathVariable("oId") String oId) throws Exception {
        String success = orderService.delivery(oId) ? "成功" : "失败";

        return "订单发货" + success + "， 订单Id: " + oId;
    }

    @PutMapping("/{oId}/received")
    public String receivedOrder(@PathVariable("oId") String oId) throws Exception {
        String success = orderService.received(oId) ? "成功" : "失败";

        return "订单收货" + success + "， 订单Id: " + oId;
    }
}
