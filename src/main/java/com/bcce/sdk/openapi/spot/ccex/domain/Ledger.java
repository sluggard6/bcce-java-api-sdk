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
public class Ledger {

    /**
     * 订单id
     */
    private Long id;
    /**
     * 创建时间
     */
    private String createdDate;
    /**
     * 金额
     */
    private String amount;
    /**
     * 账户余额
     */
    private String balance;
    /**
     * 账单类型
     */
    private String type;
    /**
     * 详情信息
     */
    private Details details;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Details {
        /**
         * 订单 id
         */
        private Long orderId;
        /**
         * 币对 id
         */
        private String productId;
    }
}
