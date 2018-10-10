package com.eleven.five.mapper;

import com.eleven.five.entity.HotColdNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong
 */
@Repository
public interface HotColdNumberMapper extends JpaRepository<HotColdNumber,Integer> {

}
