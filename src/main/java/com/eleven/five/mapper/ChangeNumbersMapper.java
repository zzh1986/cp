package com.eleven.five.mapper;

import com.eleven.five.entity.ChangeNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong
 */
@Repository
public interface ChangeNumbersMapper extends JpaRepository<ChangeNumbers,Integer> {
}
