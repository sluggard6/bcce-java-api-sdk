package com.bcce.sdk.openapi.ws.domain;

import com.bcce.sdk.openapi.spot.ccex.domain.Order;
import lombok.Data;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
@Data
public class OrdersInfo extends ParamRequest {
    /**
     * order
     */
    private Order data;
}
