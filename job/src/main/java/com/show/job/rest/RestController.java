package com.show.job.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.show.job.service.DirectService;

// 測試RESTful API
@org.springframework.web.bind.annotation.RestController
public class RestController {

    // 測試Autowired，是否抓到@Component
    @Autowired 
    DirectService directService;

    // 測試基本Rest API功能
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ReqRespVo search(@RequestBody ReqRespVo req){
        return directService.handleRest(req);
    }
    
    // 測試用的Request和Response格式
    public static class ReqRespVo{
        public String v1;
        public Integer v2;
    }
}
