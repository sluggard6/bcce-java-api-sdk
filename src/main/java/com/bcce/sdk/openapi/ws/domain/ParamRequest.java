package com.bcce.sdk.openapi.ws.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParamRequest<T> {

    /**
     * key
     */
    private String api_key;

    /**
     * pass
     */
    private String passphrase;

    /**
     * token
     */
    private String token;


    /**
     * biz type, support: spot
     */
    private String biz;

    /**
     * subscribe type,support:depth/candles/tickers/fills/orders/assets
     */
    private String type;

    /**
     * base currency code
     */
    private String base;

    /**
     * quote currency code
     */
    private String quote;

    /**
     * candles type
     */
    private String granularity;

    /**
     * zip
     */
    private Boolean zip = false;

    /**
     * since timesmtp
     */
    private String since;

    /**
     * sub channel
     */
    private String channel;

    /**
     * data
     */
    private T data;

}
