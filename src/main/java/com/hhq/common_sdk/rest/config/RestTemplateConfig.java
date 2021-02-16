package com.hhq.common_sdk.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Value("${httpconfig.connetTimeOut:1000}")
    private long connetTimeOut;

    @Value("${httpconfig.readTimeOut:1000}")
    private long readTimeOut;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){

        return restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .setConnectTimeout(Duration.ofMillis(connetTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut))
                .build();
    }
}
