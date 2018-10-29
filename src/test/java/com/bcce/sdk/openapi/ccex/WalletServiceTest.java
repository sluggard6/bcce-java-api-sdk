package com.bcce.sdk.openapi.ccex;

import com.bcce.sdk.openapi.common.BaseTest;
import com.bcce.sdk.openapi.spot.ccex.domain.Account;
import com.bcce.sdk.openapi.wallet.domain.*;
import com.bcce.sdk.openapi.wallet.domain.DepositAddress;
import com.bcce.sdk.openapi.wallet.domain.DepositRecord;
import com.bcce.sdk.openapi.wallet.domain.WithdrawInfo;
import com.bcce.sdk.openapi.wallet.domain.WithdrawRecord;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public class WalletServiceTest extends BaseTest {

    @Test
    public void testDepositAddress() throws IOException {
        final DepositAddress address = this.bcceClient.wallet().wallet().generateDepositAddress("btc");
        System.out.println(address);
    }

    @Test
    public void testGetDepositRecord() throws IOException {
        final String currency = "BTC";
        final Page<DepositRecord> records = this.bcceClient.wallet().wallet().getDepositRecord("btc",
                "1JrBWZ9NJZUazNzZuc44KSU8eswYSUKryy1",1 , 3);
        System.out.println(records);
    }

    @Test
    public void testGetWithdrawRecord() throws IOException {
        final String currency = "BTC";
        final Page<WithdrawRecord> records = this.bcceClient.wallet().wallet().getWithdrawRecord("btc",
                "1JrBWZ9NJZUazNzZuc44KSU8eswYSUKryy1",1 , 3);
        System.out.println(records);
    }

    @Test
    public void testGetWithdrawInfo() throws IOException {
        final String currency = "BTC";
        final WithdrawInfo withdrawInfo = this.bcceClient.wallet().wallet().getWithdrawInfo("btc");
        System.out.println(withdrawInfo);
    }

    @Test
    public void testWithdrawal() throws IOException {
        final String address = "2MxG55ZSi9stA7YCba2yyb31ntEBnYEcYQV";
        final String currency = "BTC";
        final BigDecimal amount = new BigDecimal("2.0181");
        final WithdrawRecord object = this.bcceClient.wallet().wallet().withdraw(currency, address,amount,"1fa3eef8-8e38-47db-8b5f-60b42f202ad51535533656371");
        System.out.println(object);
    }


    @Test
    public void testDepositRecord() throws IOException {
        final DepositRecord object = this.bcceClient.wallet().wallet().getDepositRecord("test-2");
        System.out.println(object);
    }

    @Test
    public void testWithdrawRecord() throws IOException {
        final WithdrawRecord object = this.bcceClient.wallet().wallet().getWithdrawRecord("e016e05a-0374-4497-b7ea-65afdea9ff021533110293619");
        System.out.println(object);
    }

    @Test
    public void testBalances() throws IOException {
        final List<Account> accounts = this.bcceClient.wallet().wallet().getBalances();
        System.out.println(accounts);
    }
}
