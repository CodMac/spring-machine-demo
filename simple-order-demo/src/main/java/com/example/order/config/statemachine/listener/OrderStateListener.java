package com.example.order.config.statemachine.listener;

import com.example.order.config.statemachine.schema.OrderEvent;
import com.example.order.constant.MessageHeeaderConstants;
import com.example.order.entity.Order;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * 状态转换监听器
 */
@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListener {



    @OnTransition(source = "INIT", target = "WAIT_PAYMENT")
    public boolean initTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get(MessageHeeaderConstants.ORDER_KEY_OF_HEADER);

        // 更新数据库的订单状态
        // todo

        System.out.printf("order(%s):创建订单完成%n", order.getOId());
        return true;
    }

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get(MessageHeeaderConstants.ORDER_KEY_OF_HEADER);

        // 更新数据库的订单状态
        // todo

        System.out.printf("order(%s):支付完成%n", order.getOId());
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get(MessageHeeaderConstants.ORDER_KEY_OF_HEADER);

        // 更新数据库的订单状态
        // todo

        System.out.printf("order(%s):发货完成%n", order.getOId());
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<OrderEvent> message){
        Order order = (Order) message.getHeaders().get(MessageHeeaderConstants.ORDER_KEY_OF_HEADER);

        // 更新数据库的订单状态
        // todo

        System.out.printf("order(%s):收货完成%n", order.getOId());
        return true;
    }

}
