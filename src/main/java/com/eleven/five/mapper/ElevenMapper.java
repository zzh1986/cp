package com.eleven.five.mapper;

import com.eleven.five.entity.Elevens;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-19
 */
@Repository
public interface ElevenMapper extends JpaRepository<Elevens,Integer>{
//====================================改代码主要用于统计次数===============================================================
    @Query(value = "SELECT COUNT(1) one FROM (SELECT * FROM elevens LIMIT ?1,?2) t1 WHERE one=1", nativeQuery = true)
    Integer searchOneNumberFromEleven(int offset,  int limit);

    @Query(value = "SELECT COUNT(1) two FROM (SELECT * FROM elevens LIMIT ?1,?2) t2 WHERE two=1", nativeQuery = true)
        Integer searchTwoNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT COUNT(1) three FROM (SELECT * FROM elevens LIMIT ?1,?2) t3 WHERE three=1", nativeQuery = true)
        Integer searchThreeNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT COUNT(1) four FROM (SELECT * FROM elevens LIMIT ?1,?2) t4 WHERE four=1", nativeQuery = true)
        Integer searchFourNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT COUNT(1) five FROM (SELECT * FROM elevens LIMIT ?1,?2) t5 WHERE five=1", nativeQuery = true)
        Integer searchFiveNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT COUNT(1) six FROM (SELECT * FROM elevens LIMIT ?1,?2) t6 WHERE six=1", nativeQuery = true)
        Integer searchSixNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT COUNT(1) seven FROM (SELECT * FROM elevens LIMIT ?1,?2) t7 WHERE seven=1", nativeQuery = true)
        Integer searchSevenNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT COUNT(1) eight FROM (SELECT * FROM elevens LIMIT ?1,?2) t8 WHERE eight=1", nativeQuery = true)
        Integer searchEightNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT COUNT(1) nine FROM (SELECT * FROM elevens LIMIT ?1,?2) t9 WHERE nine=1", nativeQuery = true)
        Integer searchNineNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT COUNT(1) ten FROM (SELECT * FROM elevens LIMIT ?1,?2) t0 WHERE ten=1", nativeQuery = true)
        Integer searchTenNumberFromEleven( int offset,  int limit);

     @Query(value="SELECT COUNT(1) FROM (SELECT * FROM elevens LIMIT ?1,?2) t WHERE eleven=1", nativeQuery = true)
        Integer searchElevenNumberFromEleven( int offset,  int limit);

     @Query(value = "SELECT period FROM elevens LIMIT ?1,1", nativeQuery = true)
    String searchByLatest(Integer latest);

//=========================================================================================================
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE one=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchOneThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE two=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchTwoThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE three=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchThreeThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE four=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchFourThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE five=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchFiveThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE six=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchSixThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE seven=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchSevenThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE eight=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchEightThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE nine=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchNineThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE ten=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchTenThreeTimesFromEleven(int i, int i1, int i2);
    @Query(value = "SELECT COUNT(1) FROM elevens WHERE eleven=1 AND id in (?1,?2,?3)",nativeQuery = true)
    Integer searchElevenThreeTimesFromEleven(int i, int i1, int i2);

    @Query(value = "SELECT id FROM elevens order by id asc limit 1",nativeQuery = true)
    Integer SearchFirstId();
}
