package com.bcce.sdk.openapi.spot.publics.domain;

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
public class CodeInfo {

    /**
     * 币对
     */
    private String code;
    /**
     * 基准币, 已经废弃 请使用 baseCurrency
     */
    @Deprecated
    private String baseCurrencyCode;
    /**
     * 交易币, 已经废弃 请使用 quoteCurrency
     */
    @Deprecated
    private String quoteCurrencyCode;
    /**
     * 基准币
     */
    private String baseCurrency;
    /**
     * 交易币
     */
    private String quoteCurrency;
    /**
     * 基准币最小交易量
     */
    private String baseMinSize;
    /**
     * 基准币最大交易量
     */
    private String baseMaxSize;
    /**
     * 基准币最小交易单位
     */
    private String baseIncrement;
    /**
     * 交易币最小交易单位
     */
    private String quoteIncrement;
}
