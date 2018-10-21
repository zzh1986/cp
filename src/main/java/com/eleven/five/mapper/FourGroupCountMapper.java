package com.eleven.five.mapper;

import com.eleven.five.entity.FourGroupCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-10-21
 */
@Repository
public interface FourGroupCountMapper extends JpaRepository<FourGroupCount,Integer> {
}
