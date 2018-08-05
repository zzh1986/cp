package com.eleven.five.mapper;

import com.eleven.five.entity.ElevenThreePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-05
 */
@Repository
public interface ElevenThreePeriodMapper extends JpaRepository<ElevenThreePeriod,Integer> {
}
