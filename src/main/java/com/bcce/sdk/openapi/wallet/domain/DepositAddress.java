package com.bcce.sdk.openapi.wallet.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @date 2017/12/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DepositAddress {
    /**
     *
     */
    private String address;
    /**
     * 币种：BTC、BCH 等
     */
    private String currency;

    /**
     * 判断币种是否开放充值
     */
    private boolean deposit;

    /**
     * 充值到账确认数：多少个确认充值到账
     */
    private Integer depositConfirm;

    /**
     * 提现确认数：多少个确认可以提现
     */
    private Integer withdrawConfirm;

    /**
     * 最少充值数目，少于这个数量的充值不会到账
     */
    private BigDecimal minDepositAmount;


}