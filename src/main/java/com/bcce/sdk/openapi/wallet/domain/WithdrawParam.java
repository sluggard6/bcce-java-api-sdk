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
public class WithdrawParam implements Serializable {
    /**
     *
     */
    private String address;
    /**
     *
     */
    private BigDecimal amount;

    /**
     *
     */
    private String currency;

    private String tradeNo;
}
