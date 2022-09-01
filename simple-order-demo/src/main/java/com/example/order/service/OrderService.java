package com.example.order.service;

import com.example.order.config.statemachine.schema.OrderEvent;
import com.example.order.config.statemachine.schema.OrderStatus;
import com.example.order.constant.MessageHeeaderConstants;
import com.example.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    private StateMachinePersister<OrderStatus, OrderEvent, String> stateMachinePersister;

    @Autowired
    private StateMachine<OrderStatus, OrderEvent> stateMachine;


    public void createOrder(String oId) throws Exception {
        stateMachine.startReactively().block();

        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(OrderEvent.CREATE).setHeader(MessageHeeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();

        stateMachinePersister.persist(stateMachine, oId);
    }

    public void pay(String oId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, oId);

        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(OrderEvent.PAYED).setHeader(MessageHeeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();

        stateMachinePersister.persist(stateMachine, oId);
    }

    public void delivery(String oId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, oId);

        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(OrderEvent.DELIVERY).setHeader(MessageHeeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();

        stateMachinePersister.persist(stateMachine, oId);
    }

    public void received(String oId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, oId);

        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(OrderEvent.RECEIVED).setHeader(MessageHeeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();

        stateMachinePersister.persist(stateMachine, oId);
    }
}
