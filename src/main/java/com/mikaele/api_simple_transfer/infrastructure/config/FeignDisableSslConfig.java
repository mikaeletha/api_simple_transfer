package com.mikaele.api_simple_transfer.infrastructure.config;

import feign.Client;
import feign.hc5.ApacheHttp5Client;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignDisableSslConfig {

    @Bean
    public Client feignClient() throws Exception {
        var sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(SSLContextBuilder.create()
                        .loadTrustMaterial(null, (chain, authType) -> true)
                        .build())
                .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        var connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

        return new ApacheHttp5Client(HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build());
    }
}