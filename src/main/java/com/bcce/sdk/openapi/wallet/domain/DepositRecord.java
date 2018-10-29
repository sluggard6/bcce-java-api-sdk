package com.bcce.sdk.openapi.wallet.domain;

import lombok.*;

import java.util.Date;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DepositRecord {
    /**
     *
     */
    private String txId;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String amount;
    /**
     *
     */
    private Integer confirmation;
    /**
     *
     */
    private String currency;
}