package com.example.accessingdatamysql.second;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author caiwl
 * @date 2020/4/14 9:47
 */
@Entity
@Data
public class DAT_RoomTemp {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Integer id;
    private String roomTempPointID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.000",timezone = "GMT+8")
    @Id
    private Date getTime;
    private Double roomTemp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.000",timezone = "GMT+8")
    private Date addTime;
}
