package com.example.accessingdatamysql.first;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author caiwl
 * @date 2020/4/3 8:52
 */
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface TbhistoryRepository extends JpaRepository<Tbhistory, Integer> {
    List<Tbhistory> findAllByRecordTimeBetween(Date time1, Date Time2);
//    List<Tbhistory> findAllByRecordTimeLessThanEqual(Date recordTime);
    List<Tbhistory> findAllByRecordTimeBefore(Date recordTime);
    List<Tbhistory> findAllByRecordTimeAfter(Date recordTime);
    @Query(value = "select * from tbhistory where recordTime > ?1 and recordTime <= ?2",nativeQuery = true)
    List<Tbhistory> queryTimeBetween(Date time1, Date Time2);
}
