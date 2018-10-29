package com.bcce.sdk.openapi.ccex;

import com.alibaba.fastjson.JSON;
import com.bcce.sdk.openapi.common.BaseTest;
import com.bcce.sdk.openapi.common.domain.Record;
import com.bcce.sdk.openapi.common.enums.KlineEnum;
import com.bcce.sdk.openapi.spot.publics.domain.CodeInfo;
import com.bcce.sdk.openapi.spot.publics.domain.OrderBook;
import com.bcce.sdk.openapi.spot.publics.domain.ServerTime;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public class PublicServiceTest extends BaseTest {

    static final String CODE = "LTC-BTC";

    @Test
    public void testTime() throws IOException {
        final ServerTime time = this.bcceClient.spot().publics().time();
        System.out.println(time);
    }

    @Test
    public void testTicker() throws IOException {
        final Object[] ticker = this.bcceClient.spot().publics().ticker(PublicServiceTest.CODE);
        System.out.println(JSON.toJSONString(ticker));
    }

    @Test
    public void testOrderBook() throws IOException {
        final Integer size = 100;
        final OrderBook orderBook = this.bcceClient.spot().publics().orderbook(PublicServiceTest.CODE, size);
        System.out.println(JSON.toJSONString(orderBook));
    }

    @Test
    public void testProducts() throws IOException {
        final List<CodeInfo> codeInfos = this.bcceClient.spot().publics().products();
        System.out.println(JSON.toJSONString(codeInfos));
    }

    @Test
    public void testFills() throws IOException {
        final Record<List<Object[]>, Integer> fills = this.bcceClient.spot().publics()
                .fills(PublicServiceTest.CODE, null, null, null);
        System.out.println(JSON.toJSONString(fills));
    }

    @Test
    public void testCandles() throws IOException {
        final List<Object[]> candles = this.bcceClient.spot().publics()
                .candles(PublicServiceTest.CODE, KlineEnum.DAY, "2018-01-01 00:00:00", "2018-04-27 00:00:00");
        System.out.println(JSON.toJSONString(candles));
    }

}
