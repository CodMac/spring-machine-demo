package org.example;

import org.example.state_monitor.stateMachine.schema.Events;
import org.example.state_monitor.stateMachine.schema.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class MonitorApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.startReactively().block();

        Message<Events> message1 = MessageBuilder.withPayload(Events.E1).setHeader("foo", "bar").build();
        stateMachine.sendEvent(Mono.just(message1)).subscribe();

        Message<Events> message2 = MessageBuilder.withPayload(Events.E2).setHeader("foo", "bar").build();
        stateMachine.sendEvent(Mono.just(message2)).subscribe();
    }
}