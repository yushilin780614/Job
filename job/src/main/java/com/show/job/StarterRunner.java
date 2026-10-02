package com.show.job;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.show.job.entity.Message;
import com.show.job.repository.MessageRepository;

// 因為用H2資料庫來測試，所以需要一些預設資料
@Component
public class StarterRunner implements CommandLineRunner{
    @Autowired 
    MessageRepository messageRepository;

    // 製作測試資料
    public void run(String[] args){
        List<Message> mL=new ArrayList<>();

        Message m=new Message();
        mL.add(m);
        int count=6;
        m.createDate=LocalDateTime.now().minusDays(count).minusHours(count).minusMinutes(count);
        m.likedCount=count;
        m.uid="cake";
        m.name="蛋糕人";
        m.shortMsg="Hello World...";
        m.fullMsg="Hello World. 今天剛來請多指教";

        m=new Message();
        mL.add(m);
        count=5;
        m.createDate=LocalDateTime.now().minusDays(count).minusHours(count).minusMinutes(count);
        m.likedCount=count;
        m.uid="hotdog";
        m.name="熱狗人";
        m.shortMsg="Welcome...";
        m.fullMsg="Welcome! 歡迎加入";

        m=new Message();
        mL.add(m);
        count=4;
        m.createDate=LocalDateTime.now().minusDays(count).minusHours(count).minusMinutes(count);
        m.likedCount=count;
        m.uid="cake";
        m.name="蛋糕人";
        m.shortMsg="可以問個問題...";
        m.fullMsg="可以問個問題嗎？我有些不懂";

        m=new Message();
        mL.add(m);
        count=3;
        m.createDate=LocalDateTime.now().minusDays(count).minusHours(count).minusMinutes(count);
        m.likedCount=count;
        m.uid="hotdog";
        m.name="熱狗人";
        m.shortMsg="OK...";
        m.fullMsg="OK! 當然可以，我們是一個團隊";

        m=new Message();
        mL.add(m);
        count=2;
        m.createDate=LocalDateTime.now().minusDays(count).minusHours(count).minusMinutes(count);
        m.likedCount=count;
        m.uid="cake";
        m.name="蛋糕人";
        m.shortMsg="可否見面...";
        m.fullMsg="可否見面直接詢問，因為有關Spring Boot，所以有點複雜？";

        m=new Message();
        mL.add(m);
        count=1;
        m.createDate=LocalDateTime.now().minusDays(count).minusHours(count).minusMinutes(count);
        m.likedCount=count;
        m.uid="hotdog";
        m.name="熱狗人";
        m.shortMsg="好啊...";
        m.fullMsg="好啊! 我在位子上，直接過來吧！";

        messageRepository.saveAllAndFlush(mL);
    }
}
