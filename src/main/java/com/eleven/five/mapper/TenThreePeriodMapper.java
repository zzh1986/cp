package com.eleven.five.mapper;

import com.eleven.five.entity.TenThreePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-29
 */
@Repository
public interface TenThreePeriodMapper extends JpaRepository<TenThreePeriod,Integer> {

}
