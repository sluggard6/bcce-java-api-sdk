package com.bcce.sdk.openapi.spot.publics.service;

import com.bcce.sdk.openapi.common.domain.Record;
import com.bcce.sdk.openapi.common.enums.KlineEnum;
import com.bcce.sdk.openapi.spot.publics.domain.CodeInfo;
import com.bcce.sdk.openapi.spot.publics.domain.OrderBook;
import com.bcce.sdk.openapi.spot.publics.domain.ServerTime;

import java.io.IOException;
import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public interface PublicService {

    /**
     * 获取服务端系统时间 get /spot/public/time
     *
     * @return com.bcce.sdk.openapi.ccex.domain.ServerTime
     * @see ServerTime
     */
    ServerTime time() throws IOException;

    /**
     * 获取行情信息 get /spot/public/products/{code}/ticker
     *
     * @param code 币对(ltc-btc,btc-usdt等)
     * @return 数组 [time, high, low, last, volume, open, close]
     */
    Object[] ticker(String code) throws IOException;

    /**
     * 获取深度信息 get /spot/public/products/{code}/orderbook
     *
     * @param code 币对
     * @param size 返回数据条数
     * @return 订单深度信息 com.bcce.sdk.openapi.ccex.domain.OrderBook
     * @see OrderBook
     */
    OrderBook orderbook(String code, Integer size) throws IOException;

    /**
     * 获取币对信息 get /spot/public/products
     *
     * @return 币对信息列表 com.bcce.sdk.openapi.ccex.domain.CodeInfo
     * @see CodeInfo
     */
    List<CodeInfo> products() throws IOException;

    /**
     * 获取交易信息
     *
     * @param code   币对 get /spot/public/products/{code}/fills
     * @param before 交易 id 游标
     * @param after  交易 id 游标
     * @param limit  返回交易信息数量
     * @return 交易信息列表 [[price, amount, side time, id], ...]
     */
    Record<List<Object[]>, Integer> fills(String code, Integer before, Integer after, Integer limit) throws IOException;

    /**
     * 获取 kline 信息
     *
     * @param code  币对 get /spot/public/products/{code}/candles
     * @param type  kline 类型
     * @param start 开始时间
     * @param end   结束时间
     * @return kline 数组 [[time, low, high, open, close, volume], ...]
     */
    List<Object[]> candles(String code, KlineEnum type, String start, String end) throws IOException;
}
