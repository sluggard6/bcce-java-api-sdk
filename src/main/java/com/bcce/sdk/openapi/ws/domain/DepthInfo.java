package com.bcce.sdk.openapi.ws.domain;

import com.bcce.sdk.openapi.spot.publics.domain.OrderBook;
import lombok.Data;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
@Data
public class DepthInfo extends ParamRequest {

    /**
     * data
     */
    private OrderBook data;
}
