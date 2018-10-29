package com.bcce.sdk.openapi.wallet;

import com.bcce.sdk.openapi.client.ApiClient;
import com.bcce.sdk.openapi.wallet.service.WalletService;
import com.bcce.sdk.openapi.wallet.service.impl.WalletServiceImpl;

/**
 * @author bcce-team
 * @data 2018/7/25
 */
public class WalletApiFacade {
    private final ApiClient apiClient;

    public WalletApiFacade(final ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public WalletService wallet() {
        return new WalletEndpoint(this.apiClient).wallet();
    }


    public static class WalletEndpoint {
        private final ApiClient apiClient;

        public WalletEndpoint(final ApiClient apiClient) {
            this.apiClient = apiClient;
        }


        public WalletService wallet() {
            return new WalletServiceImpl(this.apiClient);
        }

    }
}
