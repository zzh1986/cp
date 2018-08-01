package com.eleven.five.mapper;

import com.eleven.five.entity.ThreePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-20
 */
@Repository
public interface ThreePeriodMapper extends JpaRepository<ThreePeriod,Integer> {
}
