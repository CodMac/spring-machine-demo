package org.example.state_monitor.stateMachine.listener;

import org.example.state_monitor.stateMachine.schema.Events;
import org.example.state_monitor.stateMachine.schema.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class ListenerConfig {
    @Autowired
    StateMachine<States, Events> stateMachine;

    @Bean
    public StateMachineApplicationEventListener contextListener() {
        return new StateMachineApplicationEventListener();
    }

    @Bean
    public StateMachineListener stateMachineEventListener() {
        StateMachineListener listener = new StateMachineListener();
        stateMachine.addStateListener(listener);
        return listener;
    }
}