package com.example.accessingdatamysql.first;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

/**
 * @author caiwl
 * @date 2020/4/3 8:51
 */
@Entity // This tells Hibernate to make a table out of this class
@Data
public class Tbhistory {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    private String ID;
    private String DeviceName;
    private Integer DeviceAddr;
    private Integer NodeId;
    private Double Hum;
    private Double Tem;
    private Boolean CoordinateType;
    private Double Lng;
    private Double Lat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.000",timezone = "GMT+8")
    private Date recordTime;
    private Boolean IsAlarmData;
    private Boolean Source;



}
