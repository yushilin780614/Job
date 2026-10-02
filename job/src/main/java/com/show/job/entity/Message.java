package com.show.job.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 為了測試從資料庫抓取多筆資料，然後顯示在畫面中
@Entity 
@Table(name="ExchangeMessage")
public class Message {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    public String id;

    // 訊息建立時間，手動塞假日期時間，避免都一樣
    @Column 
    public LocalDateTime createDate;

    // 使用者帳號
    @Column 
    public String uid;

    // 使用者名稱
    @Column 
    public String name;

    // 訊息按讚數
    @Column 
    public Integer likedCount;

    // 簡短訊息內容
    @Column 
    public String shortMsg;

    // 完整訊息內容
    @Column
    public String fullMsg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(Integer likedCount) {
        this.likedCount = likedCount;
    }

    public String getShortMsg() {
        return shortMsg;
    }

    public void setShortMsg(String shortMsg) {
        this.shortMsg = shortMsg;
    }

    public String getFullMsg() {
        return fullMsg;
    }

    public void setFullMsg(String fullMsg) {
        this.fullMsg = fullMsg;
    }

        
}
