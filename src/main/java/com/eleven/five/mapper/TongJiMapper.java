package com.eleven.five.mapper;

import com.eleven.five.entity.TongJi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-07-07
 */
@Repository
public interface TongJiMapper extends JpaRepository<TongJi,Integer> {
}
