package org.example.state_monitor.stateMachine;

import org.example.state_monitor.stateMachine.monitor.TestStateMachineMonitor;
import org.example.state_monitor.stateMachine.schema.Events;
import org.example.state_monitor.stateMachine.schema.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.monitor.StateMachineMonitor;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine(name = "stateMachine")
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.S1)
                .state(States.S1,
                        stateContext -> {
                            System.out.println("s1 state -- before action");},
                        stateContext -> {
                            System.out.println("s1 state -- after action");}
                )
                .state(States.S2,
                        stateContext -> {
                    System.out.println("s2 state -- before action");},
                        stateContext -> {
                    System.out.println("s2 state -- after action");}
                )
                .end(States.S3);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(States.S1).target(States.S2)
                    .event(Events.E1)
                    .action(stateContext -> {
                        System.out.println("transitions s1->s2 action running");
                    })
                    .and()
                .withExternal()
                    .source(States.S2).target(States.S3)
                    .event(Events.E2)
                    .action(stateContext -> {
                        System.out.println("transitions s2->s3 action running");
                    });
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("StateMachineListener: State change to " + to.getId());
            }
        };
    }

    @Bean
    public StateMachineMonitor<States, Events> stateMachineMonitor() {
        return new TestStateMachineMonitor();
    }
}
