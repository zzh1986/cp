package com.eleven.five.mapper;

import com.eleven.five.entity.TenRepeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-09-16
 */
@Repository
public interface TenRepeatMapper extends JpaRepository<TenRepeat,Integer> {
    @Query(value = "SELECT repeat_num FROM ten_repeat ORDER BY period ASC",nativeQuery = true)
    Integer[] findRepeatNumbers();
}
