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


- 