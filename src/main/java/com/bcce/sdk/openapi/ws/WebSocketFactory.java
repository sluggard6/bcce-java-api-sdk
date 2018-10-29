package com.bcce.sdk.openapi.ws;

import com.bcce.sdk.openapi.ws.impl.SpotWebSocketClientImpl;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
public class WebSocketFactory {

    public static SpotWebSocketClient newWebSocketClient() {
        return new SpotWebSocketClientImpl();
    }

}
