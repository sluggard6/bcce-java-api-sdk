package com.bcce.sdk.openapi.spot.ccex.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bcce.sdk.openapi.client.ApiClient;
import com.bcce.sdk.openapi.common.domain.Record;
import com.bcce.sdk.openapi.common.enums.HttpHeader;
import com.bcce.sdk.openapi.spot.ccex.api.AccountApi;
import com.bcce.sdk.openapi.spot.ccex.domain.Account;
import com.bcce.sdk.openapi.spot.ccex.domain.Ledger;
import com.bcce.sdk.openapi.spot.ccex.domain.WithdrawalParam;
import com.bcce.sdk.openapi.spot.ccex.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public class AccountServiceImpl implements AccountService {
    private final AccountApi accountApi;

    public AccountServiceImpl(final ApiClient client) {
        this.accountApi = client.create(AccountApi.class);
    }


    @Override
    public List<Account> assets() throws IOException {
        final Call<List<Account>> call = this.accountApi.assets();
        return call.execute().body();
    }

    @Override
    public Record<List<Ledger>, Long> ledger(final String currencyCode, Long before, Long after, Integer limit) throws IOException {

        if (currencyCode == null) {
            throw new IllegalArgumentException(" currency code is required ");
        }

        if (before != null && before < 0L) {
            before = null;
        }

        if (after != null && after < 0L) {
            after = null;
        }

        if (limit == null || limit < 0 || limit > 100) {
            limit = 100;
        }

        final Call<List<Ledger>> call = this.accountApi.ledger(currencyCode, before, after, limit);
        final Response<List<Ledger>> response = call.execute();

        final String cbBefore = response.headers().get(HttpHeader.CB_BEFORE);
        final String cbAfter = response.headers().get(HttpHeader.CB_AFTER);

        return Record.<List<Ledger>, Long>builder()
                .data(response.body())
                .before(StringUtils.isEmpty(cbBefore) ? 0L : Long.valueOf(cbBefore))
                .after(StringUtils.isEmpty(cbAfter) ? 0L : Long.valueOf(cbAfter)).build();
    }

    @Override
    public JSONObject withdrawal(final String address, final String currencyCode, final BigDecimal amount) throws IOException {
        if (StringUtils.isEmpty(address)) {
            throw new IllegalArgumentException(" The address is required ");
        }
        if (StringUtils.isEmpty(currencyCode)) {
            throw new IllegalArgumentException(" The currency code is required ");
        }
        if (amount == null) {
            throw new IllegalArgumentException(" The amount is required ");
        }
        if (BigDecimal.ZERO.compareTo(amount) >= 0) {
            throw new IllegalArgumentException(" The amount needs to be greater than 0 ");
        }
        final WithdrawalParam body = WithdrawalParam.builder().address(address).currencyCode(currencyCode).amount(amount).build();
        return this.accountApi.withdrawal(body).execute().body();
    }
}
