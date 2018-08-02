package com.eleven.five.mapper;

import com.eleven.five.entity.TenTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-21
 */
@Repository
public interface TenTimesMapper extends JpaRepository<TenTimes,Integer> {

    @Query(value = "select period from ten_times order by period desc limit 1",nativeQuery = true)
    String findPeriodLatest();

    @Query(value = "select * from ten_times order by period asc limit 1",nativeQuery = true)
    TenTimes findPeriodOldest();
}
