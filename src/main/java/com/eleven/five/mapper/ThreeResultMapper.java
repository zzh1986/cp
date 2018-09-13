package com.eleven.five.mapper;

import com.eleven.five.entity.ThreeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong
 */
@Repository
public interface ThreeResultMapper extends JpaRepository<ThreeResult,Integer> {

}
