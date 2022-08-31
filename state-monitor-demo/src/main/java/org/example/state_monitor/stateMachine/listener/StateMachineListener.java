package org.example.state_monitor.stateMachine.listener;

import org.example.state_monitor.stateMachine.schema.Events;
import org.example.state_monitor.stateMachine.schema.States;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * Listener方式二：继承StateMachineListener
 */
public class StateMachineListener extends StateMachineListenerAdapter<States, Events> {

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        System.out.println("StateMachineListener: stateChanged");
    }

    @Override
    public void stateEntered(State<States, Events> state) {
    }

    @Override
    public void stateExited(State<States, Events> state) {
    }

    @Override
    public void transition(Transition<States, Events> transition) {
    }

    @Override
    public void transitionStarted(Transition<States, Events> transition) {
    }

    @Override
    public void transitionEnded(Transition<States, Events> transition) {
    }

    @Override
    public void stateMachineStarted(StateMachine<States, Events> stateMachine) {
    }

    @Override
    public void stateMachineStopped(StateMachine<States, Events> stateMachine) {
    }

    @Override
    public void eventNotAccepted(Message<Events> event) {
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
    }

    @Override
    public void stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
    }

    @Override
    public void stateContext(StateContext<States, Events> stateContext) {
    }
}
