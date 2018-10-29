package com.bcce.sdk.openapi.ccex;

import com.alibaba.fastjson.JSONObject;
import com.bcce.sdk.openapi.client.RestClient;
import com.bcce.sdk.openapi.common.BaseTest;
import com.bcce.sdk.openapi.common.domain.Record;
import com.bcce.sdk.openapi.spot.ccex.domain.Account;
import com.bcce.sdk.openapi.spot.ccex.domain.Ledger;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public class AccountServiceTest extends BaseTest {

    @Test
    public void testAssets() throws IOException {
        final List<Account> accounts = this.bcceClient.spot().ccex().account()
                .assets();
        System.out.println(accounts);
    }

    @Test
    public void testLedger() throws IOException {
        final String currency = "BTC";
        final Record<List<Ledger>, Long> accounts = this.bcceClient.spot().ccex().account()
                .ledger(currency, null, null, 100);
        System.out.println(accounts);
    }

    @Test
    public void testWithdrawal() throws IOException {
        final String address = "13800000000";
        final String currency = "BTC";
        final BigDecimal amount = new BigDecimal("2.0181");
        final JSONObject object = this.bcceClient.spot().ccex().account()
                .withdrawal(address, currency, amount);
        System.out.println(object);
    }

    public void testExample() throws IOException {
        final RestClient bcceClient = RestClient.builder()
                .configuration(this.parameter)
                .build();

        bcceClient.spot()
                .ccex()
                .order().postOrder(null);

        bcceClient.spot()
                .ccex()
                .account().assets();

        bcceClient.spot()
                .publics()
                .ticker("BTC");

        bcceClient.spot()
                .margin();
    }
}
