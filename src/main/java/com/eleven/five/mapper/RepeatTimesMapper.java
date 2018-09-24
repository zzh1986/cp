package com.eleven.five.mapper;

import com.eleven.five.entity.RepeatTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-09-24
 */
@Repository
public interface RepeatTimesMapper extends JpaRepository<RepeatTimes,Integer> {
}
