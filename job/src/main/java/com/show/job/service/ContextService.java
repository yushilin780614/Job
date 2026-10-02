package com.show.job.service;

import java.time.Instant;

// 測試@Configuration，製作由Spring管理的Bean
public class ContextService {

    public Instant now(){
        return Instant.now();
    }
}
