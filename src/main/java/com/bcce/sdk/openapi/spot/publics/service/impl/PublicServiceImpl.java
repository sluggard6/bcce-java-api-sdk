package com.bcce.sdk.openapi.spot.publics.service.impl;

import com.bcce.sdk.openapi.client.ApiClient;
import com.bcce.sdk.openapi.common.domain.Record;
import com.bcce.sdk.openapi.common.enums.HttpHeader;
import com.bcce.sdk.openapi.common.enums.KlineEnum;
import com.bcce.sdk.openapi.spot.publics.api.PublicApi;
import com.bcce.sdk.openapi.spot.publics.domain.CodeInfo;
import com.bcce.sdk.openapi.spot.publics.domain.OrderBook;
import com.bcce.sdk.openapi.spot.publics.domain.ServerTime;
import com.bcce.sdk.openapi.spot.publics.service.PublicService;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public class PublicServiceImpl implements PublicService {

    private final PublicApi productApi;

    public PublicServiceImpl(final ApiClient client) {
        this.productApi = client.create(PublicApi.class);
    }

    @Override
    public ServerTime time() throws IOException {
        final Call<ServerTime> call = this.productApi.time();
        return call.execute().body();
    }

    @Override
    public Object[] ticker(final String code) throws IOException {
        this.checkCode(code);
        return this.productApi.ticker(code).execute().body();
    }

    @Override
    public OrderBook orderbook(final String code, Integer size) throws IOException {
        this.checkCode(code);
        if (size == null || size < 0) {
            size = 200;
        }
        return this.productApi.orderbook(code, size).execute().body();
    }

    @Override
    public List<CodeInfo> products() throws IOException {
        return this.productApi.products().execute().body();
    }

    @Override
    public Record<List<Object[]>, Integer> fills(final String code, Integer before, Integer after, Integer limit) throws IOException {

        this.checkCode(code);

        if (before != null && before < 0) {
            before = null;
        }

        if (after != null && after < 0) {
            after = null;
        }

        if (limit != null && limit < 0) {
            limit = 100;
        }
        final Response<List<Object[]>> response = this.productApi.fills(code, before, after, limit).execute();
        final String cbBefore = response.headers().get(HttpHeader.CB_BEFORE);
        final String cbAfter = response.headers().get(HttpHeader.CB_AFTER);

        return Record.<List<Object[]>, Integer>builder()
                .data(response.body())
                .before(StringUtils.isEmpty(cbBefore) ? 0 : Integer.valueOf(cbBefore))
                .after(StringUtils.isEmpty(cbAfter) ? 0 : Integer.valueOf(cbAfter)).build();
    }

    @Override
    public List<Object[]> candles(final String code, final KlineEnum type, final String start, final String end) throws IOException {
        this.checkCode(code);
        return this.productApi.candles(code, type.getTypeName(), start, end).execute().body();
    }

    private void checkCode(final String code) {
        // 币对对不允许为空
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException(" code is required ");
        }
    }
}
