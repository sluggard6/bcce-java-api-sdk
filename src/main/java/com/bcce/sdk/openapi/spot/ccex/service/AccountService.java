package com.bcce.sdk.openapi.spot.ccex.service;

import com.alibaba.fastjson.JSONObject;
import com.bcce.sdk.openapi.common.domain.Record;
import com.bcce.sdk.openapi.spot.ccex.domain.Account;
import com.bcce.sdk.openapi.spot.ccex.domain.Ledger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public interface AccountService {

    /**
     * 获取账户信息 GET /spot/ccex/account/assets
     *
     * @return List<com.bcce.sdk.openapi.ccex.domain.Account>
     * @see Account
     */
    List<Account> assets() throws IOException;

    /**
     * 获取用户账单信息 GET /spot/ccex/account/{currency}/ledger
     *
     * @param currencyCode 币种，必填
     * @param before       用于分页请求，非必填 账单 id 游标，用于获取 before 之后的账单数据，如共有 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] 十条数据，before = 3 limit = 3 则返回 4，5，6 三条数据
     * @param after        用于分页请求，非必填 账单 id 游标，用于获取 after 之前的账单数据，如共有 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] 十条数据，after = 7 limit = 3 则返回 4，5，6 三条数据
     * @param limit        分页请求的数量，非必填
     * @return 账单列表
     */
    Record<List<Ledger>, Long> ledger(String currencyCode, Long before, Long after, Integer limit) throws IOException;


    /**
     * 提现 POST /spot/ccex/account/withdraw
     *
     * @param address      提现地址
     * @param currencyCode 币种
     * @param amount       提现金额
     * @return
     * @throws IOException
     */
    JSONObject withdrawal(String address, String currencyCode, BigDecimal amount) throws IOException;
}
