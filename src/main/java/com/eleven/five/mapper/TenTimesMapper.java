package com.eleven.five.mapper;

import com.eleven.five.entity.TenTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-21
 */
@Repository
public interface TenTimesMapper extends JpaRepository<TenTimes,Integer> {

    @Query(value = "select period from ten_times order by period desc limit 1",nativeQuery = true)
    String findPeriodLatest();

    @Query(value = "select * from ten_times order by period asc limit 1",nativeQuery = true)
    TenTimes findPeriodOldest();

    @Query(value = "select * from ten_times order by period desc limit 1",nativeQuery = true)
    TenTimes findTenTimesLatest();

    @Query(value = "select one_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectFirstColumn();
    @Query(value = "select two_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectSecondColumn();
    @Query(value = "select three_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectThirdColumn();
    @Query(value = "select four_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectForthColumn();
    @Query(value = "select five_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectFifthColumn();
    @Query(value = "select six_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectSixthColumn();
    @Query(value = "select seven_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectSeventhColumn();
    @Query(value = "select eight_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectEighthColumn();
    @Query(value = "select nine_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectNinthColumn();
    @Query(value = "select ten_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectTenthColumn();
    @Query(value = "select eleven_ten from ten_times order by period asc ",nativeQuery = true)
    Integer[] selectEleventhColumn();

}
