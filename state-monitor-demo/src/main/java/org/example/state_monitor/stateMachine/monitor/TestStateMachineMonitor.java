package org.example.state_monitor.stateMachine.monitor;

import org.example.state_monitor.stateMachine.schema.Events;
import org.example.state_monitor.stateMachine.schema.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.monitor.AbstractStateMachineMonitor;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class TestStateMachineMonitor extends AbstractStateMachineMonitor<States, Events> {

    @Override
    public void transition(StateMachine<States, Events> stateMachine, Transition<States, Events> transition, long duration) {
        State<States, Events> source = transition.getSource();
        State<States, Events> target = transition.getTarget();

        String sourceState = source == null ? "nil" : source.getId().name();
        String targetState = target == null ? "nil" : target.getId().name();

        System.out.printf("StateMachineMonitor: transition: %s -> %s\n", sourceState, targetState);
    }

    @Override
    public void action(StateMachine<States, Events> stateMachine, Function<StateContext<States, Events>, Mono<Void>> action, long duration) {

    }
}
