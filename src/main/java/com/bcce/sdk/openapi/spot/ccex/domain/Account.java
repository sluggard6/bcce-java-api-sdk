package com.bcce.sdk.openapi.spot.ccex.domain;


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
@AllArgsConstructor
public class Account {
    /**
     * 账户id，目前暂时为空
     */
    private Long id;
    /**
     * 币种 如：BTC
     */
    private String currencyCode;
    /**
     * 账户总资产
     */
    private String balance;
    /**
     * 可用余额
     */
    private String available;
    /**
     * 账户冻余额
     */
    private String hold;

}
