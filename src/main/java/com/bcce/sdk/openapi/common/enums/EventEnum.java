package com.bcce.sdk.openapi.common.enums;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
public enum EventEnum {

    /**
     * 登录
     */
    SIGNIN("signin"),
    /**
     * 订阅
     */
    SUBSCRIBE("subscribe"),
    /**
     * 取消订阅
     */
    UNSUBSCRIBE("unsubscribe");

    private final String typeName;

    EventEnum(final String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

}
