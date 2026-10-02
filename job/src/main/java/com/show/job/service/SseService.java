package com.show.job.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

// 用來處理所有Server-Sent Event，相關的事務
@Component 
public class SseService {

    // 儲存建立過的Server-Sent Event物件
    private final Map<String, SseEmitter> sseM=new HashMap<>();

    // 根據IP或其他識別名稱，儲存建立過的Server-Sent Event物件
    public SseEmitter addSse(String ip){
        SseEmitter sse=new SseEmitter();
        sseM.put(ip, sse);
        return sse;
    }

    // 發送Server-Sent Event，要求index.html更新Request的次數
    public void sendCount(String ip, int count){
        SseEmitter sse=sseM.get(ip);
        if(sse!=null){
        try{
                sse.send(SseEmitter.event().name("countUpdate").data(count).build());
            }catch(Throwable e){
                e.printStackTrace();
                sseM.remove(ip);
            }
        }
    }
}
