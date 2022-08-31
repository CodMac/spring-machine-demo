package org.example.state_monitor.stateMachine.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.statemachine.event.OnTransitionStartEvent;
import org.springframework.statemachine.event.StateMachineEvent;

/**
 * Listener方式一： 实现ApplicationListener
 */
public class StateMachineApplicationEventListener implements ApplicationListener<StateMachineEvent> {

    @Override
    public void onApplicationEvent(StateMachineEvent event) {
        /**
         * 应用程序上下文事件类
         * OnTransitionStartEvent
         * OnTransitionEvent
         * OnTransitionEndEvent
         * OnStateExitEvent
         * OnStateEntryEvent
         * OnStateChangedEvent
         * OnStateMachineStart
         * OnStateMachineStop
         */
        if(event instanceof OnTransitionStartEvent){
            System.out.println("StateMachineApplicationEventListener: OnTransitionStartEvent");
        }
    }
}