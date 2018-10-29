package com.bcce.sdk.openapi.spot.ccex.api;

import com.alibaba.fastjson.JSONObject;
import com.bcce.sdk.openapi.spot.ccex.domain.Account;
import com.bcce.sdk.openapi.spot.ccex.domain.Ledger;
import com.bcce.sdk.openapi.spot.ccex.domain.WithdrawalParam;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public interface AccountApi {

    @GET("account/assets")
    Call<List<Account>> assets();


    @GET("account/{currencyCode}/ledger")
    Call<List<Ledger>> ledger(@Path("currencyCode") String currencyCode,
                              @Query("before") Long before,
                              @Query("after") Long after,
                              @Query("limit") Integer limit);

    @POST("account/withdraw")
    Call<JSONObject> withdrawal(@Body WithdrawalParam body);
}
