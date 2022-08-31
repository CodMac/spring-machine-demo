package com.example;

import com.example.stateMachine.schema.Events;
import com.example.stateMachine.schema.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;


@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {

        Message<Events> message1 = MessageBuilder.withPayload(Events.E1).setHeader("foo", "bar").build();
        stateMachine.sendEvent(Mono.just(message1)).subscribe();

        Message<Events> message2 = MessageBuilder.withPayload(Events.E2).setHeader("foo", "bar").build();
        stateMachine.sendEvent(Mono.just(message2)).subscribe();
    }
}