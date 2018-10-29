package com.bcce.sdk.openapi.ws;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
@FunctionalInterface
public interface WebSocketApiCallBack<T> {

    /**
     * websocket call back respose
     *
     * @param response
     */
    void onResponse(T response);

    /**
     * web socket failure
     *
     * @param cause
     */
    default void onFailure(final Throwable cause) {
    }
}
