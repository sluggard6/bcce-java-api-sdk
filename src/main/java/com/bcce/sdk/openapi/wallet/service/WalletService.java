package com.bcce.sdk.openapi.wallet.service;

import com.bcce.sdk.openapi.spot.ccex.domain.Account;
import com.bcce.sdk.openapi.wallet.domain.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bcce-team
 * @data 2018/7/25
 */
public interface WalletService {

    /**
     * generate a new address according to currency
     * @param currency
     * @return
     */
    DepositAddress generateDepositAddress(String currency)throws IOException;


    /**
     * get deposit records
     * @param currency must not be null
     * @param address can be null
     * @param pageNum the first page default
     * @param pageSize get 10 records default
     * @return
     */
    Page<DepositRecord>  getDepositRecord(String currency, String address, int pageNum, int pageSize)throws IOException;

    /**
     * 根据tradeNo 查询充值记录
     * @param tradeNo
     * @return
     * @throws IOException
     */
    DepositRecord  getDepositRecord(String tradeNo)throws IOException;

    /**
     * get some param, such as fee ,mini withdraw amount when withdraw
     * @param currency
     * @return
     */
    WithdrawInfo getWithdrawInfo(String currency)throws IOException;

    /**
     * apply for withdraw
     * @param currency
     * @param address
     * @param amount
     * @return
     */
    @Deprecated
    WithdrawRecord withdraw(String currency, String address, BigDecimal amount)throws IOException;


    /**
     *
     * @param currency
     * @param address
     * @param amount
     * @param tradeNo 用来标识唯一一笔提现，由发起方生成
     * @return
     * @throws IOException
     */
    WithdrawRecord withdraw(String currency, String address, BigDecimal amount,String tradeNo)throws IOException;


    /**
     * get withdraw records
     * @param currency
     * @param address
     * @param pageNum the first page default
     * @param pageSize get 10 records default
     * @return
     */
    Page<WithdrawRecord>  getWithdrawRecord(String currency, String address, int pageNum, int pageSize) throws IOException;


    /**
     * 根据tradeNo查询提现记录
     * @param tradeNo
     * @return
     * @throws IOException
     */
    WithdrawRecord  getWithdrawRecord(String tradeNo) throws IOException;


    /**
     * get balances
     * @return
     * @throws IOException
     */
    List<Account> getBalances() throws IOException;


}
