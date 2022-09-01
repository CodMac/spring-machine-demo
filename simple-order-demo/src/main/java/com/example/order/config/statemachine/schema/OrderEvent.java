package com.example.order.config.statemachine.schema;

public enum OrderEvent {
    /**
     * 创建
     */
    CREATE,
    /**
     * 支付
     */
    PAYED,
    /**
     * 发货
     */
    DELIVERY,
    /**
     * 确认收货
     */
    RECEIVED;
}
