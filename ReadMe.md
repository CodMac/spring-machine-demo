#### `spring machine demo `
demo基于 spring-statemachine 3.2.0

#### `module 简介`
- `hello-world`： 最简单demo
    ```
    1. 定义 State 和 Event 的枚举
    2. 配置了 State 间的转换规则
    3. 定义了一个简单的listener
    ```


- `persist-in-memory-demo`: 使用本地内存方式（HashMap）持久化状态机
    ```
    1. 使用HashMap的本地内存方式持久化状态机
    ```

- `persist-redis-demo`: 使用redis方式持久化状态机
    ```
    1. 使用Redis内存方式持久化状态机
    
    可以看出 `persist-redis-demo` 和 `persist-in-memory-demo` 的区别
    仅仅为 `StateMachinePersister` Bean的具体实现替换
    ```

- `state-monitor-demo`: 演示了 monitor 和 listener 的实现和生命周期
    ```
    1. 演示了 `state状态` 的 before/after action
    2. 演示了 `transitions转换规则` 的 action
    3. 演示了 `monitor` 的实现
    4. 演示了 `listener` 的实现(2种方式)
    ```
  
- `simple-order-demo`: 简单的订单状态系统
  ```
  1. 定义了订单的状态生命周期
  2. 通过 restful接口进行订单 `创建`， `支付`，`发货`， `收货` 控制
  ```