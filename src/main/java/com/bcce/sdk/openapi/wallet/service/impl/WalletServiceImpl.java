package com.bcce.sdk.openapi.wallet.service.impl;

import com.bcce.sdk.openapi.client.ApiClient;
import com.bcce.sdk.openapi.spot.ccex.domain.Account;
import com.bcce.sdk.openapi.wallet.api.WalletApi;
import com.bcce.sdk.openapi.wallet.domain.*;
import com.bcce.sdk.openapi.wallet.service.WalletService;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author bcce-team
 * @data 2018/7/25
 */
public class WalletServiceImpl implements WalletService {
    private final WalletApi walletApi;

    public WalletServiceImpl(final ApiClient client) {
        this.walletApi = client.create(WalletApi.class);
    }


    /**
     * generate a new address according to currency
     * @param currency
     * @return
     */
    @Override
    public DepositAddress generateDepositAddress(String currency) throws IOException {
        if(StringUtils.isEmpty(currency)){
            throw new IllegalArgumentException("currency is required");
        }

        return walletApi.generateDepositAddress(currency).execute().body();
    }


    /**
     * get deposit records
     * @param currency must not be null
     * @param address can be null
     * @param pageNum the first page default
     * @param pageSize get 10 records default
     * @return
     */
    @Override
    public Page<DepositRecord> getDepositRecord(String currency, String address, int pageNum, int pageSize) throws IOException{
        if(StringUtils.isEmpty(currency)){
            throw new IllegalArgumentException("currency is required");
        }
        if(pageNum <= 0){
            pageNum = 1;
        }

        if(pageSize <= 0 || pageSize > 50){
            pageNum = 10;
        }

        return walletApi.getDepositRecord(currency,address,pageNum,pageSize).execute().body();

    }

    @Override
    public DepositRecord getDepositRecord(String tradeNo) throws IOException{
        if(StringUtils.isNotEmpty(tradeNo)){
            return walletApi.getDepositRecord(tradeNo).execute().body();
        }else {
            return null;
        }
    }

    /**
     * get some param, such as fee ,mini withdraw amount when withdraw
     * @param currency
     * @return
     */
    @Override
    public WithdrawInfo getWithdrawInfo(String currency)throws IOException{
        if(StringUtils.isEmpty(currency)){
            throw new IllegalArgumentException("currency is required");
        }
        return walletApi.getWithdrawInfo(currency).execute().body();
    }

    /**
     * apply for withdraw
     * @param currency
     * @param address
     * @param amount
     * @return
     */
    @Override
    @Deprecated
    public WithdrawRecord withdraw(String currency, String address, BigDecimal amount)throws IOException{
        String tradeNo = UUID.randomUUID().toString() + System.currentTimeMillis();
        return withdraw(currency,address,amount,tradeNo);
    }

    /**
     * apply for withdraw
     * @param currency
     * @param address
     * @param amount
     * @return
     */
    @Override
    public WithdrawRecord withdraw(String currency, String address, BigDecimal amount,String tradeNo)throws IOException{
        if (StringUtils.isEmpty(address)) {
            throw new IllegalArgumentException(" The address is required ");
        }
        if (StringUtils.isEmpty(tradeNo)) {
            throw new IllegalArgumentException(" The address is required ");
        }
        if (StringUtils.isEmpty(currency)) {
            throw new IllegalArgumentException(" The currency code is required ");
        }
        if (amount == null) {
            throw new IllegalArgumentException(" The amount is required ");
        }
        if (BigDecimal.ZERO.compareTo(amount) >= 0) {
            throw new IllegalArgumentException(" The amount needs to be greater than 0 ");
        }
        final WithdrawParam body = WithdrawParam.builder().address(address).currency(currency).amount(amount).tradeNo(tradeNo).build();
        return this.walletApi.withdraw(body).execute().body();
    }


    /**
     * get withdraw records
     * @param currency
     * @param address
     * @param pageNum the first page default
     * @param pageSize get 10 records default
     * @return
     */
    @Override
    public Page<WithdrawRecord>  getWithdrawRecord(String currency, String address, int pageNum, int pageSize)throws IOException{
        if(StringUtils.isEmpty(currency)){
            throw new IllegalArgumentException("currency is required");
        }
        if(pageNum <= 0){
            pageNum = 1;
        }

        if(pageSize <= 0 || pageSize > 50){
            pageNum = 10;
        }

        return walletApi.getWithdrawRecord(currency,address,pageNum,pageSize).execute().body();
    }
    @Override
    public WithdrawRecord  getWithdrawRecord(String tradeNo)throws IOException{
        if(StringUtils.isNotEmpty(tradeNo)){
            return walletApi.getWithdrawRecord(tradeNo).execute().body();
        }else {
            return null;
        }

    }


    /**
     * get balances
     * @return
     * @throws IOException
     */
    @Override
    public List<Account> getBalances() throws IOException{
        return walletApi.getBalances().execute().body();
    }

}
