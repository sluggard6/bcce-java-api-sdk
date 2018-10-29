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
public class WithdrawRecord {
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
    private String fee;
    /**
     *
     */
    private String currency;
    /**
     * 系统间交互的唯一标识，防止发送重复交易
     */
    private String tradeNo;
}