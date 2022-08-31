package org.example.redis_memory.stateMachine.persist;

import org.example.redis_memory.stateMachine.schema.Events;
import org.example.redis_memory.stateMachine.schema.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class PersistConfig {

    @Bean
    public StateMachinePersist<States, Events, String> stateMachinePersist(RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<States, Events> repository = new RedisStateMachineContextRepository<States, Events>(connectionFactory);
        return new RepositoryStateMachinePersist<States, Events>(repository);
    }

    /**
     * 配置状态机持久化实现
     * @return
     */
    @Bean
    public StateMachinePersister<States, Events, String> redisStateMachinePersister(StateMachinePersist<States, Events, String> stateMachinePersist) {
        return new RedisStateMachinePersister<States, Events>(stateMachinePersist);
    }
}
