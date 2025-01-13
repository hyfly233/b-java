package com.hyfly.dolphinscheduler.sdk.remote;

import com.google.common.base.Strings;
import com.hyfly.dolphinscheduler.sdk.remote.request.DefaultHttpClientRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.RequestContent;

/**
 * the factory to create http client(rest template)
 */
public class HttpClientFactory {

    public static final String HTTP_CLIENT_APACHE = "apache";
    public static final String HTTP_CLIENT_SPRING = "spring";

    /**
     * get rest template to operate dolphin scheduler
     *
     * @param type use HttpClientFactory.HTTP_CLIENT_APACHE and HttpClientFactory.HTTP_CLIENT_SPRING
     * @return
     */
    public com.hyfly.dolphinscheduler.sdk.remote.DolphinsRestTemplate getRestTemplate(String type) {
        if (Strings.isNullOrEmpty(type)) {
            throw new IllegalArgumentException("type is null.");
        }
        switch (type) {
            case HTTP_CLIENT_APACHE:
                return getApacheRestTemplate();
            case HTTP_CLIENT_SPRING:
                return getSpringRestTemplate();
            default:
                throw new UnsupportedOperationException("now only support apache and spring.");
        }
    }

    /**
     * get rest template based on apache http client
     *
     * @return
     */
    public com.hyfly.dolphinscheduler.sdk.remote.DolphinsRestTemplate getApacheRestTemplate() {

        final RequestConfig defaultConfig = RequestConfig.custom().build();

        return new com.hyfly.dolphinscheduler.sdk.remote.DolphinsRestTemplate(
                new DefaultHttpClientRequest(
                        HttpClients.custom()
                                .addInterceptorLast(new RequestContent(true))
                                .setDefaultRequestConfig(defaultConfig)
                                .build(),
                        defaultConfig));
    }

    // TODO
    public DolphinsRestTemplate getSpringRestTemplate() {
        throw new UnsupportedOperationException();
    }
}
