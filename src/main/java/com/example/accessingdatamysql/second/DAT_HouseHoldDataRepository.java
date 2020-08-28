package com.example.accessingdatamysql.second;

        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.Date;

/**
 * @author caiwl
 * @date 2020/4/14 9:49
 */
public interface DAT_HouseHoldDataRepository extends JpaRepository<DAT_HouseHoldData, Integer> {
    DAT_HouseHoldData findByHouseHoldIDAndRoomTempAndGetTime(String houseHoldID, Double roomTemp, Date getTime);
}
