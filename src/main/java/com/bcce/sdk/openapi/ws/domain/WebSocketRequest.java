package com.bcce.sdk.openapi.ws.domain;

import com.bcce.sdk.openapi.common.enums.EventEnum;
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
public class WebSocketRequest {

    /**
     * event type, support: signin,subscribe,unsubscribe
     */
    private EventEnum event;

    /**
     * websocket url
     */
    private String wsBaseUrl;

    /**
     * params
     */
    private ParamRequest params;

}
