package com.eleven.five.mapper;

import com.eleven.five.entity.Adjacent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-09-16
 */
@Repository
public interface AdjacentMapper extends JpaRepository<Adjacent,Integer> {
    @Query(value = "SELECT repeat_num FROM adjacent ORDER BY period ASC",nativeQuery = true)
    Integer[] findRepeatNumbers();
}
