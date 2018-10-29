package com.bcce.sdk.openapi.spot.ccex.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderParam {
    /**
     * 币对如 etc_eth
     */
    private String code;
    /**
     * 买卖类型 buy/sell
     */
    private String side;
    /**
     * 订单类型 限价单 limit 市价单 market
     */
    private String type;
    /**
     * 交易数量
     */
    private String size;
    /**
     * 限价单使用 价格
     */
    private String price;
    /**
     * 市价单使用 价格
     */
    private String funds;
}
