package com.show.job.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.show.job.entity.Count;
import com.show.job.entity.Message;
import com.show.job.repository.CountRepository;
import com.show.job.repository.MessageRepository;
import com.show.job.repository.MessageRepository.MappingVo;
import com.show.job.service.ContextService;
import com.show.job.service.SseService;

import jakarta.servlet.http.HttpServletRequest;




// 測試顯示頁面功能
@Controller 
public class FirstController {

    // 測試Autowired，是否抓到用@Configuration配合@Bean
    @Autowired 
    ContextService contextService;

    @Autowired 
    MessageRepository messageRepository;

    @Autowired
    SseService sseService;

    @Autowired
    CountRepository countRepository;


    // 首頁
    @GetMapping("/")
    public String index(Model model){

        List<MappingVo> dL=messageRepository.queryShowData();
        System.out.println("dL.size()："+dL.size());

        model.addAttribute("nowDateTime", contextService.now());
        model.addAttribute("msgL", dL);
        return "index";
    }

    // 讓Index.html用ajax取得完整資料
    @PostMapping("/msg")
    @ResponseBody 
    public Message getFullMsg(@RequestBody Map<String, String> entity) {
        String id=entity.get("id");
        
        return messageRepository.getFullMsg(id);
    }
    
    // 讓前端index.html取得Count資料所需的Server-Sent Event物件
    @GetMapping("/viewCount")
    public SseEmitter getCount(HttpServletRequest req) {
        // 取得
        SseEmitter re=sseService.addSse(req.getRemoteAddr());

        // 從H2資料庫，取得目前Request的次數
        List<Count> resultL = countRepository.findByAddress(req.getRemoteAddr());
        int currentCount = 0;
        if(resultL != null && !resultL.isEmpty()){currentCount=resultL.get(0).count;}

        // 等index.html建立好Server-Sent Event監聽器後，發送Server-Sent Event給index.html
        SendCount vo=new SendCount();
        vo.sse=re;
        vo.count=currentCount;
        Thread t=new Thread(vo);
        t.start();
        
        return re;
    }
    
    // 為了留時間給index.html，讓其建立監聽器
    class SendCount implements Runnable{

        public SseEmitter sse;
        public Integer count;

        @Override
        public void run() {
            try{
                Thread.sleep(500);
                sse.send(SseEmitter.event().name("countUpdate").data(count).build());
            }catch(Throwable e){ e.printStackTrace(); }
        }
        
    }
}
