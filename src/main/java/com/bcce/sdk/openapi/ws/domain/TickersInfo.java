package com.bcce.sdk.openapi.ws.domain;

import lombok.Data;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
@Data
public class TickersInfo extends ParamRequest {

    /**
     * data
     */
    private Object[] data;

}
