package com.example.order.config.statemachine.persist;

import com.example.order.config.statemachine.schema.OrderEvent;
import com.example.order.config.statemachine.schema.OrderStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class PersistConfig {

    @Bean
    public StateMachinePersist<OrderStatus, OrderEvent, String> stateMachinePersist(RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<OrderStatus, OrderEvent> repository = new RedisStateMachineContextRepository<OrderStatus, OrderEvent>(connectionFactory);
        return new RepositoryStateMachinePersist<OrderStatus, OrderEvent>(repository);
    }

    /**
     * 配置状态机持久化实现
     * @return
     */
    @Bean
    public StateMachinePersister<OrderStatus, OrderEvent, String> redisStateMachinePersister(StateMachinePersist<OrderStatus, OrderEvent, String> stateMachinePersist) {
        return new RedisStateMachinePersister<OrderStatus, OrderEvent>(stateMachinePersist);
    }
}
