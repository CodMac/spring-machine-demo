package org.example.service;

import org.example.stateMachine.schema.Events;
import org.example.stateMachine.schema.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StateMachineService {

    @Autowired
    private StateMachinePersister<States, Events, String> stateMachinePersister;


    @Autowired
    private StateMachine<States, Events> stateMachine;

    public void create() throws Exception {

        stateMachine.startReactively().block();
        Mono<Message<Events>> message1 = Mono.just(MessageBuilder.withPayload(Events.E1).build());
        stateMachine.sendEvent(message1).blockLast();
        stateMachinePersister.persist(stateMachine, "stateMachine-01");


        stateMachine.startReactively().block();
        Mono<Message<Events>> message2 = Mono.just(MessageBuilder.withPayload(Events.E1).build());
        stateMachine.sendEvent(message2).blockLast();
        Mono<Message<Events>> message3 = Mono.just(MessageBuilder.withPayload(Events.E2).build());
        stateMachine.sendEvent(message3).blockLast();
        stateMachinePersister.persist(stateMachine, "stateMachine-02");

    }

    public void getState(String stateMachineId) throws Exception {
        stateMachine.startReactively().block();
        stateMachinePersister.restore(stateMachine, stateMachineId);
        State<States, Events> state = stateMachine.getState();
        System.out.printf("stateMachine( %s )'s current-state：%s\n", stateMachineId, state.getId().name());
    }
}
