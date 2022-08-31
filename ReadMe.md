#### `spring machine demo `

#### module
- `hello-world`： 最简单demo
```
1. @EnableStateMachine + 实现 EnumStateMachineConfigurerAdapter 或 StateMachineConfigurerAdapter
构建一个状态机实例
2. StateMachineConfigurationConfigurer<States, Events> config
配置并开启状态机实例
3. StateMachineStateConfigurer<States, Events> states 
构建定义状态机实例的状态
4. StateMachineTransitionConfigurer<States, Events> transitions 
构建状态机实例的状态转换规则
5. StateMachineListener<States, Events> listener() {....}
定义一个状态转换监听器
6. 使用 StateMachine<States, Events> stateMachine 实例触发事件
```


- `persist-in-memory-demo`: 使用本地内存方式（HashMap）持久化状态机
```
1. 新建InMemoryStateMachinePersist类， 实现 StateMachinePersist
重写write(...)及read(...)方法
2. 定义一个Bean： StateMachinePersister
返回新建InMemoryStateMachinePersist类的实例
3. 通过StateMachinePersister实例， 执行 persist 或 restore 
持久化 或 重置状态机
```

- `persist-redis-demo`: 使用redis方式持久化状态机
```
1. 引入`spring-boot-starter-data-redis`
自动配置redis
2. 定义一个Bean： StateMachinePersister
返回RepositoryStateMachinePersist实例
3. 通过StateMachinePersister实例， 执行 persist 或 restore 
持久化 或 重置状态机

可以看出 `persist-redis-demo` 和 `persist-in-memory-demo` 的区别
仅仅为 `StateMachinePersister` Bean的具体实现替换
```