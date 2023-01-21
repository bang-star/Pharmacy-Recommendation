package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@Configuration
public class RetryConfig {

//    RetryTemplate 를 사용할 경우
//    @Bean
//    public RetryTemplate retryTemplate() {
//        return new RetryTemplate();
//    }

}
