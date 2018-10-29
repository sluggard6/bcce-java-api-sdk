package com.bcce.sdk.openapi.client;

import com.bcce.sdk.openapi.ws.WebSocketFactory;
import com.bcce.sdk.openapi.ws.domain.WebSocketRequest;
import org.apache.commons.lang3.Validate;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
public class WebSocketClient {

    private final WebSocketRequest webSocketRequest;

    public WebSocketClient(final Builder builder) {

        this.webSocketRequest = builder.webSocketRequest;
        Validate.notNull(this.webSocketRequest, "webSocketRequest is null");
        Validate.notNull(this.webSocketRequest.getEvent(), "event is null");
    }

    public WebSocketFactory spot() {
        return new WebSocketFactory();
    }

    public static WebSocketClient.Builder builder() {
        return new WebSocketClient.Builder();
    }

    public static class Builder {
        private WebSocketRequest webSocketRequest;

        public WebSocketClient.Builder configuration(final WebSocketRequest value) {
            this.webSocketRequest = value;
            return this;
        }

        public WebSocketClient build() {
            return new WebSocketClient(this);
        }
    }
}
