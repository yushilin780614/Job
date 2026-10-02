package com.show.job.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 記錄收到多少次Request
@Entity 
@Table(name="Request Count")
public class Count {
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    // 次數
    @Column(name="request_count")
    public Integer count;

    // IP或其他可識別的名稱
    @Column(name="ip_address")
    public String address;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
}
