package com.show.job.service;

import org.springframework.stereotype.Component;

import com.show.job.rest.RestController;

// 測試@Component
@Component
public class DirectService {

    public RestController.ReqRespVo handleRest(RestController.ReqRespVo vo){
        RestController.ReqRespVo re=new RestController.ReqRespVo();
        re.v1=vo.v1+"Resp";
        re.v2=vo.v2+10;
        return re;
    } 
}
