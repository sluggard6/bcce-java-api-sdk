package com.bcce.sdk.openapi.spot.publics.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bcce-sdk-team
 * @date 2017/12/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderBook  implements Cloneable {
    /**
     * 卖单
     */
    private List<String[]> asks;

    /**
     * 买单
     */
    private List<String[]> bids;

    @Override
    public OrderBook clone() {
        try {
            return (OrderBook) super.clone();
        } catch (final CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
