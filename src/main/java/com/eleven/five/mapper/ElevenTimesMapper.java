package com.eleven.five.mapper;

import com.eleven.five.entity.ElevenTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-05
 */
@Repository
public interface ElevenTimesMapper extends JpaRepository<ElevenTimes,Integer> {

    @Query(value = "select period from eleven_times order by period desc limit 1",nativeQuery = true)
    String findPeriodLatest();

    @Query(value = "select * from eleven_times order by period asc limit 1",nativeQuery = true)
    ElevenTimes findPeriodOldest();

}
