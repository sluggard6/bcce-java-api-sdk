package com.bcce.sdk.openapi.ws;

import com.bcce.sdk.openapi.ws.domain.ParamRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.IOException;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
public class WebSocketApiListener<T> extends WebSocketListener {

    private final WebSocketApiCallBack<T> callback;

    private final Class<T> result;

    private boolean closing = false;

    public WebSocketApiListener(final WebSocketApiCallBack<T> callback, final Class<T> c) {
        this.callback = callback;
        this.result = c;
    }

    @Override
    public void onMessage(final WebSocket webSocket, final String text) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final ParamRequest request = mapper.readValue(text, ParamRequest.class);
            if (request != null && request.getChannel() != null) {
                return;
            }
            final T event = mapper.readValue(text, this.result);
            this.callback.onResponse(event);
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void onClosing(final WebSocket webSocket, final int code, final String reason) {
        this.closing = true;
    }

    @Override
    public void onFailure(final WebSocket webSocket, final Throwable t, final Response response) {
        if (!this.closing) {
            this.callback.onFailure(t);
        }
    }
}
