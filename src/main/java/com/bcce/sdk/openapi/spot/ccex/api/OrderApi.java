package com.bcce.sdk.openapi.spot.ccex.api;

import com.bcce.sdk.openapi.spot.ccex.domain.AddOrderResult;
import com.bcce.sdk.openapi.spot.ccex.domain.CancelOrderParam;
import com.bcce.sdk.openapi.spot.ccex.domain.Order;
import com.bcce.sdk.openapi.spot.ccex.domain.OrderParam;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public interface OrderApi {

    /**
     * 下单接口
     *
     * @param body 请求结构体 com.bcce.sdk.openapi.ccex.domain.OrderParam
     * @return 成功 返回 订单id
     * @see OrderParam
     * @see AddOrderResult
     */
    @POST("orders")
    Call<AddOrderResult> postOrder(@Body OrderParam body);

    /**
     * 根据订单 id 撤单
     *
     * @param orderId 订单 id
     * @param body    请求结构体 com.bcce.sdk.openapi.ccex.domain.CancelOrderParam
     * @return 无
     * @see CancelOrderParam
     */
    @HTTP(method = "DELETE", path = "orders/{orderId}", hasBody = true)
    Call<Void> deleteOrder(@Path("orderId") Long orderId, @Body CancelOrderParam body);

    /**
     * 批量撤单，会根据 code 撤单
     *
     * @param body 请求结构体 com.bcce.sdk.openapi.ccex.domain.CancelOrderParam
     * @return 无
     * @see CancelOrderParam
     */
    @HTTP(method = "DELETE", path = "orders", hasBody = true)
    Call<Void> deleteOrders(@Body CancelOrderParam body);

    /**
     * 根据订单 id 获取订单信息
     *
     * @param orderId 订单 id
     * @param code    币对(ltc-btc,btc-usdt等)
     * @return 订单信息 com.bcce.sdk.openapi.ccex.domain.Order
     * @see Order
     */
    @GET("orders/{orderId}")
    Call<Order> getOrder(@Path("orderId") Long orderId, @Query("code") String code);

    /**
     * 分页获取订单信息
     *
     * @param code   币对
     * @param status 状态
     * @param before 游标 订单 id
     * @param after  游标 订单 id
     * @param limit  获取数量
     * @return 订单信息列表
     * @see Order
     */
    @GET("orders")
    Call<List<Order>> getOrders(@Query("code") String code,
                                @Query("status") String status,
                                @Query("before") Long before,
                                @Query("after") Long after,
                                @Query("limit") Integer limit);
}
