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

    /**
     * 创建订单
     * @param oId
     * @return
     * @throws Exception
     */
    public boolean createOrder(String oId) throws Exception {
        restoreStateMachine(oId);

        String currentState = stateMachine.getState().getId().name();
        if(!currentState.equals(OrderStatus.INIT.name())){
            return false;
        }

        sendEvent(oId, OrderEvent.CREATE);

        persistStateMachine(oId);
        return true;
    }

    /**
     * 支付订单
     * @param oId
     * @return
     * @throws Exception
     */
    public boolean pay(String oId) throws Exception {
        restoreStateMachine(oId);

        String currentState = stateMachine.getState().getId().name();
        if(!currentState.equals(OrderStatus.WAIT_PAYMENT.name())){
            return false;
        }

        sendEvent(oId, OrderEvent.PAYED);

        persistStateMachine(oId);
        return true;
    }

    /**
     * 订单发货
     * @param oId
     * @return
     * @throws Exception
     */
    public boolean delivery(String oId) throws Exception {
        restoreStateMachine(oId);

        String currentState = stateMachine.getState().getId().name();
        if(!currentState.equals(OrderStatus.WAIT_DELIVER.name())){
            return false;
        }

        sendEvent(oId, OrderEvent.DELIVERY);

        persistStateMachine(oId);
        return true;
    }

    /**
     * 订单收货
     * @param oId
     * @return
     * @throws Exception
     */
    public boolean received(String oId) throws Exception {
        restoreStateMachine(oId);

        String currentState = stateMachine.getState().getId().name();
        if(!currentState.equals(OrderStatus.WAIT_RECEIVE.name())){
            return false;
        }

        sendEvent(oId, OrderEvent.RECEIVED);

        persistStateMachine(oId);
        return true;
    }


    private void restoreStateMachine(String oId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);
    }
    private void sendEvent(String oId, OrderEvent event) {
        Order order = new Order(oId);
        Mono<Message<OrderEvent>> message = Mono.just(MessageBuilder.withPayload(event).setHeader(MessageHeaderConstants.ORDER_KEY_OF_HEADER, order).build());
        stateMachine.sendEvent(message).subscribe();
    }
    private void persistStateMachine(String oId) throws Exception {
        stateMachinePersister.persist(stateMachine, MessageHeaderConstants.STATE_MACHINE_CACHE_KEY_PREFIX + oId);
    }

}
