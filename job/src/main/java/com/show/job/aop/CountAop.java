package com.show.job.aop;

import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.show.job.entity.Count;
import com.show.job.repository.CountRepository;
import com.show.job.service.SseService;

// 利用AOP記錄Request的次數
@Aspect 
@Component
public class CountAop {

    @Autowired 
    CountRepository countRepository;

    // 利用Server-Sent Event，把該台裝置發的Request次數，發給index.html顯示
    @Autowired
    SseService sseService;

    // 只計算Controller和RESTful API的Request次數
    @Before("(execution(* com.show.job.controller..*(..)) || execution(* com.show.job.rest..*(..))) && !execution(* com.show.job.controller.FirstController.getCount(..))")
    public void count(){
        // IP或其他可識別的名稱
        String ipaddress="NoneOrLocal";
        RequestAttributes req=RequestContextHolder.getRequestAttributes();
        if(req!=null){
            try{
                // 取得IP Address
                ServletRequestAttributes reqAttr=(ServletRequestAttributes)req;
                ipaddress=reqAttr.getRequest().getRemoteAddr();
            }catch(Throwable e){
                // 取得IP Address失敗後的行為
                ipaddress="UnknowRequestSource";
            }
        }

        // 累加計算Request的次數，並存入H2資料庫中
        List<Count> resultL=countRepository.findByAddress(ipaddress);
        Count result=new Count();
        result.address=ipaddress;
        result.count=0;
        if(resultL!=null && resultL.size()>0){ result=resultL.get(0); }
        result.count+=1;
        countRepository.saveAndFlush(result);

        // 發送相對應的Server-Sent Event，讓index.html更新數字
        sseService.sendCount(ipaddress, result.count);
    }
}
