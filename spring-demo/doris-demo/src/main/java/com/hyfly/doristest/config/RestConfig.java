package com.hyfly.doristest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rt = new RestTemplate();
        rt.getMessageConverters()
                .set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return rt;
    }
}
