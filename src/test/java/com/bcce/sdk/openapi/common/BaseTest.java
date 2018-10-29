package com.bcce.sdk.openapi.common;

import com.bcce.sdk.openapi.client.RestClient;
import com.bcce.sdk.openapi.common.domain.ClientParameter;
import com.bcce.sdk.openapi.common.enums.SupportedLocaleEnum;
import org.junit.After;
import org.junit.Before;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public class BaseTest {

    /**
     * 用户 apiKey
     */
    private final String apiKey = "";
    /**
     * 用户 secretKey
     */
    private final String secretKey = "";
    /**
     * 口令
     */
    private final String passphrase = "";
    /**
     * ccex open api 根路径
     */
    private final String baseUrl = "http://localhost:8110/api/v1/";

    protected ClientParameter parameter = ClientParameter.builder()
            .apiKey(this.apiKey)
            .secretKey(this.secretKey)
            .passphrase(this.passphrase)
            .baseUrl(this.baseUrl)
            .locale(SupportedLocaleEnum.EN_US.getName())
            .build();

    protected RestClient bcceClient;

    @Before
    public void setup() {
        this.bcceClient = RestClient.builder()
                .configuration(this.parameter)
                .build();
    }

    @After
    public void tearDown() {

    }
}
