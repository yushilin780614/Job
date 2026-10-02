package com.show.job;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.show.job.service.ContextService;

// 測試@Configuration，製作由Spring管理的Bean
@Configuration 
public class ServiceConfig {

    @Bean
    public ContextService configService(){
        return new ContextService();
    }
}
