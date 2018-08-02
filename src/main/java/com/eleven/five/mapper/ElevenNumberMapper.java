package com.eleven.five.mapper;

import com.eleven.five.entity.ElevenNumber;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-19
 * 该类用于初始化相应的次数信息,
 */
@Repository
public interface ElevenNumberMapper extends JpaRepository<ElevenNumber,Integer> {
    @Query(value = "select * from eleven_number where sort like ?1",nativeQuery = true)
    List<ElevenNumber> findPeriodLikeFourSort(String target);
}
