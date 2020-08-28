package com.example.accessingdatamysql.second;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author caiwl
 * @date 2020/4/14 9:47
 */
@Entity
@Data
public class DAT_HouseHoldData {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Integer id;
    private String houseHoldID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.000",timezone = "GMT+8")
    @Id
    private Date getTime;
    private Double setTemp;
    private Double roomTemp;
    private Double averageRoomTemp;
    private Double cumulativeHeat;
    private Double supplyTemp;
    private Double backTemp;
    private Integer valveState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.000",timezone = "GMT+8")
    private Date addTime;
}
