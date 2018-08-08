package com.eleven.five.mapper;

import com.eleven.five.entity.TenNumber;
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
public interface TenNumberMapper extends JpaRepository<TenNumber,Integer> {
    @Query(value = "select * from ten_number where sort like ?1",nativeQuery = true)
    List<TenNumber> findPeriodLikeFourSort(String target);
}
