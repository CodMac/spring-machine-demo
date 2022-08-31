package org.example.stateMachine.persist;

import org.example.stateMachine.schema.Events;
import org.example.stateMachine.schema.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class PersistConfig {

    /**
     * 配置状态机持久化实现
     * @return
     */
    @Bean
    public StateMachinePersister<States, Events, String> stateMachinePersister(){
        // 使用自定义的InMemoryStateMachinePersist， 真实环境中不应该使用HashMap的InMemory模式
        return new DefaultStateMachinePersister<>(new InMemoryStateMachinePersist());
    }
}
