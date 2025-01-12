package com.hyfly.dolphinscheduler.config;

import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.remote.DolphinsRestTemplate;
import com.hyfly.dolphinscheduler.sdk.remote.request.DefaultHttpClientRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.RequestContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Value("${ds.token}")
    private String token;

    @Value("${ds.address}")
    private String dolphinAddress;

    @Bean
    public DolphinClient dolphinClient() {
        DolphinsRestTemplate restTemplate = new DolphinsRestTemplate(
                new DefaultHttpClientRequest(
                        HttpClients.custom()
                                .addInterceptorLast(new RequestContent(true))
                                .setDefaultRequestConfig(RequestConfig.custom().build())
                                .build(),
                        RequestConfig.custom().build()));

//        return new DolphinClient(token, dolphinAddress, restTemplate);
        return new DolphinClient(token, dolphinAddress, restTemplate);
    }
}
