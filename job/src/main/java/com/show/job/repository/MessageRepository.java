package com.show.job.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.show.job.entity.Message;

// 使用Entity"Message"，操作資料庫的工具
@Repository 
public interface MessageRepository extends JpaRepository<Message, String>{

    // 取得要顯示的資料
    @Query(value="SELECT m.id id, m.createDate createDate, m.uid uid, m.name name, m.likedCount likedCount, m.shortMsg shortMsg FROM Message m ORDER BY m.createDate")
    public List<MappingVo> queryShowData();

    // 動態取得完整內容
    @Query(value="SELECT m FROM Message m WHERE id=:idValue")
    public Message getFullMsg(@Param("idValue") String id);

    // 為了只回傳指定的欄位資料，而設的物件
    public static interface MappingVo{
        String getId();
        LocalDateTime getCreateDate();
        String getUid();
        String getName();
        Integer getLikedCount();
        String getShortMsg();

        
    }
}
