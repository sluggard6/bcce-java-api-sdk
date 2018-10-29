package com.bcce.sdk.openapi.spot.ccex.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WithdrawalParam {
    /**
     * 提现金额
     */
    private BigDecimal amount;
    /**
     * 币种
     */
    private String currencyCode;
    /**
     * 地址
     */
    private String address;
}
