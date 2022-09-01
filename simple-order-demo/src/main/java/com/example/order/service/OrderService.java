package com.example.order.service;

import com.example.order.config.statemachine.schema.OrderEvent;
import com.example.order.config.statemachine.schema.OrderStatus;
import com.example.order.constant.MessageHeaderConstants;
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


    public boolean createOrder(String oId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);

        String currentState = stateMachine.getState().getId().name();
        if(!currentState.equals(OrderStatus.INIT.name())){
            return false;
        }

        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(OrderEvent.CREATE).setHeader(MessageHeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();

        stateMachinePersister.persist(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);
        return true;
    }

    public boolean pay(String oId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);

        String currentState = stateMachine.getState().getId().name();
        if(!currentState.equals(OrderStatus.WAIT_PAYMENT.name())){
            return false;
        }

        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(OrderEvent.PAYED).setHeader(MessageHeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();

        stateMachinePersister.persist(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);
        return true;
    }

    public boolean delivery(String oId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);

        String currentState = stateMachine.getState().getId().name();
        if(!currentState.equals(OrderStatus.WAIT_DELIVER.name())){
            return false;
        }

        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(OrderEvent.DELIVERY).setHeader(MessageHeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();

        stateMachinePersister.persist(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);
        return true;
    }

    public boolean received(String oId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);

        String currentState = stateMachine.getState().getId().name();
        if(!currentState.equals(OrderStatus.WAIT_RECEIVE.name())){
            return false;
        }

        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(OrderEvent.RECEIVED).setHeader(MessageHeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();

        stateMachinePersister.persist(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);
        return true;
    }
}
