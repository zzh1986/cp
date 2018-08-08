package com.eleven.five.mapper;

import com.eleven.five.entity.ThreePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-20
 */
@Repository
public interface ThreePeriodMapper extends JpaRepository<ThreePeriod,Integer> {
    @Query(value = "select * from three_period where sort_num like ?1",nativeQuery = true)
    List<ThreePeriod> findPeriodLikeFourSort(String sortStr);
}
