package com.bcce.sdk.openapi.ws.domain;

import com.bcce.sdk.openapi.spot.ccex.domain.Account;
import lombok.Data;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
@Data
public class AssetsInfo extends ParamRequest {

    /**
     * data
     */
    private Account data;
}
