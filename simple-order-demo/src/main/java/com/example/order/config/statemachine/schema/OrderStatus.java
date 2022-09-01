package com.example.order.config.statemachine.schema;

public enum OrderStatus {
    /**
     * 订单初始化
     */
    INIT,
    /**
     * 待支付
     */
    WAIT_PAYMENT,
    /**
     * 待发货
     */
    WAIT_DELIVER,
    /**
     * 待收货
     */
    WAIT_RECEIVE,
    /**
     * 订单结束
     */
    FINISH;
}
