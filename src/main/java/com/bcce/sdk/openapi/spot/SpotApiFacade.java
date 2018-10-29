package com.bcce.sdk.openapi.spot;

import com.bcce.sdk.openapi.client.ApiClient;
import com.bcce.sdk.openapi.spot.ccex.service.AccountService;
import com.bcce.sdk.openapi.spot.ccex.service.OrderService;
import com.bcce.sdk.openapi.spot.ccex.service.impl.AccountServiceImpl;
import com.bcce.sdk.openapi.spot.ccex.service.impl.OrderServiceImpl;
import com.bcce.sdk.openapi.spot.publics.service.PublicService;
import com.bcce.sdk.openapi.spot.publics.service.impl.PublicServiceImpl;

/**
 * @author bcce-team
 * @date 2017/12/01
 */
public class SpotApiFacade {
    private final ApiClient apiClient;

    public SpotApiFacade(final ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * 现货币币交易 REST API Endpoint
     *
     * @return CCEXEndpoint
     */
    public CCEXEndpoint ccex() {
        return new CCEXEndpoint(this.apiClient);
    }

    /**
     * 现货杠杆交易 REST API Endpoint
     *
     * @return MarginEndpoint
     */
    public MarginEndpoint margin() {
        return new MarginEndpoint(this.apiClient);
    }

    /**
     * 现货公共 REST API Endpoint
     *
     * @return PublicService
     */
    public PublicService publics() {
        return new PublicServiceImpl(this.apiClient);
    }

    public static class CCEXEndpoint {
        private final ApiClient apiClient;

        public CCEXEndpoint(final ApiClient apiClient) {
            this.apiClient = apiClient;
        }

        /**
         * 创建现货账户服务
         *
         * @return com.bcce.sdk.openapi.ccex.service.AccountService
         * @see AccountService
         */
        public AccountService account() {
            return new AccountServiceImpl(this.apiClient);
        }

        /**
         * 创建现货订单交易服务
         *
         * @return com.bcce.sdk.openapi.ccex.service.OrderService
         * @see OrderService
         */
        public OrderService order() {
            return new OrderServiceImpl(this.apiClient);
        }
    }

    public static class PublicEndpoint {
        private final ApiClient apiClient;

        public PublicEndpoint(final ApiClient apiClient) {
            this.apiClient = apiClient;
        }


        /**
         * 创建现货获取行情等信息服务
         *
         * @return ProductService
         * @see PublicService
         */
        public PublicService publics() {
            return new PublicServiceImpl(this.apiClient);
        }
    }

    public static class MarginEndpoint {
        private final ApiClient apiClient;

        public MarginEndpoint(final ApiClient apiClient) {
            this.apiClient = apiClient;
        }
    }
}
