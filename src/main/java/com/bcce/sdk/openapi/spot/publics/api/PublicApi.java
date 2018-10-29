package com.bcce.sdk.openapi.spot.publics.api;

import com.bcce.sdk.openapi.spot.publics.domain.CodeInfo;
import com.bcce.sdk.openapi.spot.publics.domain.OrderBook;
import com.bcce.sdk.openapi.spot.publics.domain.ServerTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public interface PublicApi {
    /**
     * 获取服务器时间
     *
     * @return 服务器时间结构体
     * @see ServerTime
     */
    @GET("public/time")
    Call<ServerTime> time();

    /**
     * 获取行情信息
     *
     * @param code 币对(ltc-btc,btc-usdt等)
     * @return 数组 [time, high, low, last, volume]
     */
    @GET("public/products/{code}/ticker")
    Call<Object[]> ticker(@Path("code") String code);

    /**
     * 获取深度信息
     *
     * @param code 币对(ltc-btc,btc-usdt等)
     * @param size 返回数据条数
     * @return 订单深度信息 com.bcce.sdk.openapi.ccex.domain.OrderBook
     * @see OrderBook
     */
    @GET("public/products/{code}/orderbook")
    Call<OrderBook> orderbook(@Path("code") String code, @Query("size") Integer size);

    /**
     * 获取币对信息
     *
     * @return 币对信息列表 com.bcce.sdk.openapi.ccex.domain.CodeInfo
     * @see CodeInfo
     */
    @GET("public/products")
    Call<List<CodeInfo>> products();

    /**
     * 获取交易信息
     *
     * @param code   币对(ltc-btc,btc-usdt等)
     * @param before 交易 id 游标
     * @param after  交易 id 游标
     * @param limit  返回交易信息数量
     * @return 交易信息列表 [[price, amount, sidem time, id], ...]
     */
    @GET("public/products/{code}/fills")
    Call<List<Object[]>> fills(@Path("code") String code,
                               @Query("before") Integer before,
                               @Query("after") Integer after,
                               @Query("limit") Integer limit);

    /**
     * 获取 kline 信息
     *
     * @param code  币对(ltc-btc,btc-usdt等)
     * @param type  kline 类型
     * @param start 开始时间
     * @param end   结束时间
     * @return kline 数组 [[time, low, high, open, close, volume], ...]
     */
    @GET("public/products/{code}/candles")
    Call<List<Object[]>> candles(@Path("code") String code,
                                 @Query("type") String type,
                                 @Query("start") String start,
                                 @Query("end") String end);
}
