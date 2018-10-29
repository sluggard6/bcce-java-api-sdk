package com.bcce.sdk.openapi.spot.publics.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerTime {
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 时间
     */
    private String iso;
}
