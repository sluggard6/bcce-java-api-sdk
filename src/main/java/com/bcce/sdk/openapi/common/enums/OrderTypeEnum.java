package com.bcce.sdk.openapi.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public enum OrderTypeEnum {
    LIMITED("limit"),
    MARKET("market");

    private final String typeName;

    private OrderTypeEnum(final String typeName) {
        this.typeName = typeName;
    }

    public static OrderTypeEnum fromName(final String typeName) {
        if (StringUtils.isEmpty(typeName)) {
            return null;
        }
        for (final OrderTypeEnum t : OrderTypeEnum.values()) {
            if (t.getTypeName().equalsIgnoreCase(typeName)) {
                return t;
            }
        }
        return null;
    }

    public String getTypeName() {
        return this.typeName;
    }
}