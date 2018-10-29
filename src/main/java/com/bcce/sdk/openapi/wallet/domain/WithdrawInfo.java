package com.bcce.sdk.openapi.wallet.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author bcce-team
 * @data 12/04/2018
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WithdrawInfo implements Serializable {
    private BigDecimal fee;

    /**
     * 最小提现数：小于这个数，不会发起提现
     */
    private BigDecimal minWithdrawAmount;

    /**
     *
     */
    private String currency;

    /**
     * 判断币种是否开放提现
     */
    private boolean withdraw;
}
