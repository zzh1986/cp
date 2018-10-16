package com.eleven.five.mapper;

import com.eleven.five.entity.NumberGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong
 */
@Repository
public interface NumberGroupMapper extends JpaRepository<NumberGroup,Integer> {

}
