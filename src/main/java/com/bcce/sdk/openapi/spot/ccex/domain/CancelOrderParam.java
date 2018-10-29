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
public class CancelOrderParam {
    /**
     * 币对
     */
    private String code;

    /**
     * 订单 id
     */
    private Long[] orderId;
}
