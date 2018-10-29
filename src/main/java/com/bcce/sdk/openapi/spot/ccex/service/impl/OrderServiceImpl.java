package com.bcce.sdk.openapi.spot.ccex.service.impl;


import com.bcce.sdk.openapi.client.ApiClient;
import com.bcce.sdk.openapi.common.domain.Record;
import com.bcce.sdk.openapi.common.enums.HttpHeader;
import com.bcce.sdk.openapi.common.enums.OrderStatusEnEnum;
import com.bcce.sdk.openapi.common.enums.OrderTypeEnum;
import com.bcce.sdk.openapi.common.enums.SideTypeEnum;
import com.bcce.sdk.openapi.spot.ccex.api.OrderApi;
import com.bcce.sdk.openapi.spot.ccex.domain.AddOrderResult;
import com.bcce.sdk.openapi.spot.ccex.domain.CancelOrderParam;
import com.bcce.sdk.openapi.spot.ccex.domain.Order;
import com.bcce.sdk.openapi.spot.ccex.domain.OrderParam;
import com.bcce.sdk.openapi.spot.ccex.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public class OrderServiceImpl implements OrderService {

    private final OrderApi orderApi;

    public OrderServiceImpl(final ApiClient client) {
        this.orderApi = client.create(OrderApi.class);
    }

    @Override
    public AddOrderResult postOrder(final OrderParam param) throws IOException {
        this.checkOrderParam(param);
        final Call<AddOrderResult> call = this.orderApi.postOrder(param);
        return call.execute().body();
    }

    @Override
    public void deleteOrder(final String code, final Long orderId) throws IOException {
        this.checkCode(code);
        this.checkOrderId(orderId);
        final CancelOrderParam param = CancelOrderParam.builder().code(code).build();
        this.orderApi.deleteOrder(orderId, param).execute();
    }

    @Override
    public void deleteOrders(final String code) throws IOException {
        this.checkCode(code);
        final CancelOrderParam param = CancelOrderParam.builder().code(code).build();
        this.orderApi.deleteOrders(param).execute();
    }

    @Override
    public void deleteOrders(String code, Long[] orderId) throws IOException {
        this.checkCode(code);
        final CancelOrderParam param = CancelOrderParam.builder().code(code).orderId(orderId).build();
        this.orderApi.deleteOrders(param).execute();
    }

    @Override
    public Order getOrder(final String code, final Long orderId) throws IOException {
        this.checkCode(code);
        this.checkOrderId(orderId);
        return this.orderApi.getOrder(orderId, code).execute().body();
    }

    @Override
    public Record<List<Order>, Long> getOrders(final String code,
                                               final OrderStatusEnEnum status,
                                               Long before,
                                               Long after,
                                               Integer limit) throws IOException {
        this.checkCode(code);
        if (status == null) {
            throw new IllegalArgumentException(" order status is required ");
        }
        if (before != null && before < 0L) {
            before = null;
        }

        if (after != null && after < 0L) {
            after = null;
        }

        if (limit != null && limit < 0) {
            limit = 100;
        }
        final Response<List<Order>> response = this.orderApi.getOrders(code, status.getTypeName(), before, after, limit).execute();
        final String cbBefore = response.headers().get(HttpHeader.CB_BEFORE);
        final String cbAfter = response.headers().get(HttpHeader.CB_AFTER);

        return Record.<List<Order>, Long>builder()
                .data(response.body())
                .before(StringUtils.isEmpty(cbBefore) ? 0L : Long.valueOf(cbBefore))
                .after(StringUtils.isEmpty(cbAfter) ? 0L : Long.valueOf(cbAfter)).build();
    }

    private void checkCode(final String code) {
        // 币对对不允许为空
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException(" code is required ");
        }
    }

    private void checkOrderId(final Long orderId) {
        // 订单 id 不允许为空
        if (orderId == null) {
            throw new IllegalArgumentException(" orderId is required ");
        }
    }

    private void checkOrderParam(final OrderParam param) {

        // 币种对不允许为空
        this.checkCode(param.getCode());

        // 获取订单的类型 limit/market
        final OrderTypeEnum orderType = OrderTypeEnum.fromName(param.getType());
        if (orderType == null) {
            throw new IllegalArgumentException(" type name error ");
        }
        // 订单买卖类型
        final SideTypeEnum orderSide = SideTypeEnum.fromName(param.getSide());
        if (orderSide == null) {
            throw new IllegalArgumentException(" side name error ");
        }

        // 限价单的价格、数量不允许为空
        if (OrderTypeEnum.LIMITED == orderType) {
            if (StringUtils.isEmpty(param.getPrice()) || StringUtils.isEmpty(param.getSize())) {
                throw new IllegalArgumentException(" The price or size of limit order cannot be empty. ");
            }
            // 市价单的买价格信息不允许为空，卖的数量不允许为空
        } else if (OrderTypeEnum.MARKET == orderType) {
            if (SideTypeEnum.BUY == orderSide && StringUtils.isEmpty(param.getFunds())) {
                throw new IllegalArgumentException(" The funds of market buy order cannot be empty. ");
            }
            if (SideTypeEnum.SELL == orderSide && StringUtils.isEmpty(param.getSize())) {
                throw new IllegalArgumentException(" The size of market sell order cannot be empty. ");
            }
        } else {
            // 其他的订单类型，目前不存在
            throw new IllegalArgumentException(" unknown order type");
        }
    }
}
