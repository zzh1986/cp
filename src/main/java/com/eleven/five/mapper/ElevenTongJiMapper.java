package com.eleven.five.mapper;

import com.eleven.five.entity.ElevenTongJi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-05
 */
@Repository
public interface ElevenTongJiMapper extends JpaRepository<ElevenTongJi,Integer> {
    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE one_ten=1", nativeQuery = true)
    Integer searchOneNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE two_ten=1", nativeQuery = true)
    Integer searchTwoNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE three_ten=1", nativeQuery = true)
    Integer searchThreeNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE four_ten=1", nativeQuery = true)
    Integer searchFourNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE five_ten=1", nativeQuery = true)
    Integer searchFiveNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE six_ten=1", nativeQuery = true)
    Integer searchSixNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE seven_ten=1", nativeQuery = true)
    Integer searchSevenNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE eight_ten=1", nativeQuery = true)
    Integer searchEightNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE nine_ten=1", nativeQuery = true)
    Integer searchNineNumberFromEleven();

    @Query(value = "SELECT COUNT(*) FROM eleven_times WHERE ten_ten=1", nativeQuery = true)
    Integer searchTenNumberFromEleven();

    @Query(value= "SELECT COUNT(*) FROM eleven_times WHERE eleven_ten=1", nativeQuery = true)
    Integer searchElevenNumberFromEleven();
}
