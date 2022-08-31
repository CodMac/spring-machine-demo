package org.example.in_memory.stateMachine.persist;

import org.example.in_memory.stateMachine.schema.Events;
import org.example.in_memory.stateMachine.schema.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class InMemoryStateMachinePersist  implements StateMachinePersist<States, Events, String> {

    private final HashMap<String, StateMachineContext<States, Events>> contexts = new HashMap<>();

    @Override
    public void write(StateMachineContext<States, Events> context, String s) throws Exception {
        contexts.put(s, context);
    }

    @Override
    public StateMachineContext<States, Events> read(String s) throws Exception {
        return contexts.get(s);
    }
}
