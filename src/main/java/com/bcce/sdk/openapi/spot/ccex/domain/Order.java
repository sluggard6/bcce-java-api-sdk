package com.bcce.sdk.openapi.spot.ccex.domain;


import lombok.Builder;
import lombok.Data;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
@Builder
@Data
public class Order {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单类型的价格信息
     */
    private String price;
    /**
     * 委托数量
     */
    private String volume;
    /**
     * 平均成交价
     */
    private String averagePrice;
    /**
     * 委托时间
     */
    private String createdDate;
    /**
     * 成交数量
     */
    private String filledVolume;
    /**
     * 订单状态 -1 已撤销 0 未成交
     */
    private String status;
    /**
     * 订单买卖类型 buy/sell
     */
    private String side;
    /**
     * 订单类型 limit/market
     */
    private String orderType;
    /**
     * 币对信息
     */
    private String code;

}
