package com.example.accessingdatamysql.second;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * @author caiwl
 * @date 2020/4/14 9:49
 */
public interface DAT_RoomTempRepository extends JpaRepository<DAT_RoomTemp, Integer> {
    DAT_RoomTemp findByRoomTempPointIDAndRoomTempAndGetTime(String roomTempPointID, Double roomTemp, Date getTime);
}
