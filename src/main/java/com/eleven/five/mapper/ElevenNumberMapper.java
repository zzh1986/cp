package com.eleven.five.mapper;

import com.eleven.five.entity.ElevenNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-19
 * 该类用于初始化相应的次数信息,
 */
@Repository
public interface ElevenNumberMapper extends JpaRepository<ElevenNumber,Integer> {
    @Query(value = "select period from eleven_number where sort like ?1",nativeQuery = true)
    String findPeriodLikeFourSort(String target);
}
