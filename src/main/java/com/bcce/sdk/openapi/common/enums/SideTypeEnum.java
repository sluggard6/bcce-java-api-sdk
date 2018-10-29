package com.bcce.sdk.openapi.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public enum SideTypeEnum {
    BUY("buy"),
    SELL("sell");

    private final String sideName;

    private SideTypeEnum(final String sideName) {
        this.sideName = sideName;
    }

    public static SideTypeEnum fromName(final String sideName) {
        if (StringUtils.isEmpty(sideName)) {
            return null;
        }
        for (final SideTypeEnum t : SideTypeEnum.values()) {
            if (t.getSideName().equalsIgnoreCase(sideName)) {
                return t;
            }
        }
        return null;
    }


    public String getSideName() {
        return this.sideName;
    }
}