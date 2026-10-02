package com.show.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.show.job.entity.Count;


// 使用Entity"Count"，操作資料庫的工具
@Repository 
public interface CountRepository extends JpaRepository<Count, Long>{

    // 根據IP或其他名稱，查出資料來
    public List<Count> findByAddress(String address);
}
