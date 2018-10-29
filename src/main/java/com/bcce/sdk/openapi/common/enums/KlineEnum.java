package com.bcce.sdk.openapi.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
public enum KlineEnum {
    MIN1("1min"),
    MIN3("3min"),
    MIN5("5min"),
    MIN15("15min"),
    MIN30("30min"),
    HUOR1("1hour"),
    HUOR2("2hour"),
    HUOR4("4hour"),
    HUOR6("6hour"),
    HUOR12("12hour"),
    DAY("day"),
    WEEK("week");

    private final String typeName;

    private KlineEnum(final String typeName) {
        this.typeName = typeName;
    }

    public static KlineEnum fromName(final String typeName) {
        if (StringUtils.isEmpty(typeName)) {
            return null;
        }
        for (final KlineEnum k : KlineEnum.values()) {
            if (k.getTypeName().equalsIgnoreCase(typeName)) {
                return k;
            }
        }
        return null;
    }

    public String getTypeName() {
        return this.typeName;
    }
}