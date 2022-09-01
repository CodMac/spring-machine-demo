package com.example.order.config.statemachine;

import com.example.order.config.statemachine.schema.OrderEvent;
import com.example.order.config.statemachine.schema.OrderStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderEvent> {

    /**
     * 配置状态
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderStatus.INIT)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    /**
     * 配置状态转换规则
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStatus.INIT).target(OrderStatus.WAIT_PAYMENT).event(OrderEvent.CREATE)
                .timer(1000)
                .and()

                .withExternal()
                .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER).event(OrderEvent.PAYED)
                .and()

                .withExternal()
                .source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE).event(OrderEvent.DELIVERY)
                .and()

                .withExternal()
                .source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderEvent.RECEIVED);
    }

}
