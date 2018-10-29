package com.bcce.sdk.openapi.ws.impl;

import com.alibaba.fastjson.JSON;
import com.bcce.sdk.openapi.common.enums.Content;
import com.bcce.sdk.openapi.common.enums.EventEnum;
import com.bcce.sdk.openapi.ws.SpotWebSocketClient;
import com.bcce.sdk.openapi.ws.WebSocketApiCallBack;
import com.bcce.sdk.openapi.ws.WebSocketApiListener;
import com.bcce.sdk.openapi.ws.domain.AssetsInfo;
import com.bcce.sdk.openapi.ws.domain.CandlesInfo;
import com.bcce.sdk.openapi.ws.domain.DepthInfo;
import com.bcce.sdk.openapi.ws.domain.FillsInfo;
import com.bcce.sdk.openapi.ws.domain.OrdersInfo;
import com.bcce.sdk.openapi.ws.domain.ParamRequest;
import com.bcce.sdk.openapi.ws.domain.TickersInfo;
import com.bcce.sdk.openapi.ws.domain.WebSocketRequest;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.util.concurrent.TimeUnit;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
public class SpotWebSocketClientImpl implements SpotWebSocketClient, Closeable {

    private final OkHttpClient client;

    public SpotWebSocketClientImpl() {
        final Dispatcher d = new Dispatcher();
        d.setMaxRequestsPerHost(100);
        this.client = new OkHttpClient.Builder()
                .dispatcher(d)
                .pingInterval(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * depth
     *
     * @param webSocketRequest
     * @param callBack
     * @return
     */
    @Override
    public Closeable depth(final WebSocketRequest webSocketRequest, final WebSocketApiCallBack<DepthInfo> callBack) {
        return this.createNewWebSocket(false, webSocketRequest, new WebSocketApiListener<>(callBack, DepthInfo.class));
    }

    /**
     * tickers
     *
     * @param webSocketRequest
     * @param callBack
     * @return
     */
    @Override
    public Closeable tickers(final WebSocketRequest webSocketRequest, final WebSocketApiCallBack<TickersInfo> callBack) {
        return this.createNewWebSocket(false, webSocketRequest, new WebSocketApiListener<>(callBack, TickersInfo.class));
    }

    /**
     * candles
     *
     * @param webSocketRequest
     * @param callBack
     * @return
     */
    @Override
    public Closeable candles(final WebSocketRequest webSocketRequest, final WebSocketApiCallBack<CandlesInfo> callBack) {
        return this.createNewWebSocket(false, webSocketRequest, new WebSocketApiListener<>(callBack, CandlesInfo.class));
    }

    /**
     * @param webSocketRequest
     * @param callBack
     * @return
     */
    @Override
    public Closeable orders(final WebSocketRequest webSocketRequest, final WebSocketApiCallBack<OrdersInfo> callBack) {
        return this.createNewWebSocket(true, webSocketRequest, new WebSocketApiListener<>(callBack, OrdersInfo.class));
    }

    /**
     * assets
     *
     * @param webSocketRequest
     * @param callBack
     * @return
     */
    @Override
    public Closeable assets(final WebSocketRequest webSocketRequest, final WebSocketApiCallBack<AssetsInfo> callBack) {
        return this.createNewWebSocket(true, webSocketRequest, new WebSocketApiListener<>(callBack, AssetsInfo.class));
    }

    /**
     * fills
     *
     * @param webSocketRequest
     * @param callBack
     * @return
     */
    @Override
    public Closeable fills(final WebSocketRequest webSocketRequest, final WebSocketApiCallBack<FillsInfo> callBack) {
        return this.createNewWebSocket(false, webSocketRequest, new WebSocketApiListener<>(callBack, FillsInfo.class));
    }

    @Override
    public void close() {
        this.client.dispatcher().executorService().shutdown();
    }

    private Closeable createNewWebSocket(final Boolean login, final WebSocketRequest webSocketRequest, final WebSocketApiListener<?> listener) {
        webSocketRequest.setWsBaseUrl(StringUtils.defaultIfBlank(webSocketRequest.getWsBaseUrl(), Content.WS_BASE_URL));
        final Request request = new Request.Builder().url(webSocketRequest.getWsBaseUrl()).build();
        final WebSocket webSocket = this.client.newWebSocket(request, listener);
        if (login) {
            final ParamRequest paramRequest = ParamRequest.builder()
                    .api_key(webSocketRequest.getParams().getApi_key())
                    .passphrase(webSocketRequest.getParams().getPassphrase())
                    .build();
            webSocket.send(JSON.toJSONString(WebSocketRequest.builder()
                    .event(EventEnum.SIGNIN)
                    .params(paramRequest)
                    .build()));
        }
        //send message
        webSocket.send(JSON.toJSONString(webSocketRequest));

        return () -> {
            final int code = 1000;
            listener.onClosing(webSocket, code, null);
            webSocket.close(code, null);
            listener.onClosed(webSocket, code, null);
        };


    }


}
