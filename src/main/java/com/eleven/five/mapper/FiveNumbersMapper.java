package com.eleven.five.mapper;

import com.eleven.five.entity.FiveNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong
 */
@Repository
public interface FiveNumbersMapper extends JpaRepository<FiveNumbers,Integer> {

    @Query(value = "select count(*) from  five_numbers where one=?1 and two=?2 and three=?3",nativeQuery = true)
    int findCountByThree(String s, String s1, String s2);
}
