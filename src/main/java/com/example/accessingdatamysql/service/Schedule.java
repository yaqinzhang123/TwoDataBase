package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.first.Tbhistory;
import com.example.accessingdatamysql.first.TbhistoryRepository;
import com.example.accessingdatamysql.second.DAT_HouseHoldData;
import com.example.accessingdatamysql.second.DAT_HouseHoldDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class Schedule {
    @Autowired
    private MainService mainService;
    @Autowired
    private TbhistoryRepository tbhistoryRepository;
//    @Scheduled(cron = "0 0 1 * * ?")
//    public   void schedule(){
//        try{
//            Date date=new   Date();//取时间
//            System.out.println(date.toString());
//            Calendar calendar   =   new GregorianCalendar();
//            calendar.setTime(date);
//            calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
//            Date date1=calendar.getTime();   //这个时间就是日期往后推一天的结果
//            System.out.println(date1.toString());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String fom1=sdf.format(date1);
//            date1=sdf.parse(fom1);
//            List<Tbhistory> list=  tbhistoryRepository.findAllByRecordTimeBetween(date1,date);
//            this.mainService.transfer(list);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }
    @Scheduled(cron = "0 0 0/1 * * ?")
    public   void scheduleHours(){
        try{
            Date date=new   Date();//取时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
            String fom1=sdf.format(date);
            date=sdf.parse(fom1);
            System.out.println(date.toString());
            Calendar calendar   =   new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.MINUTE,-61);
            Date date1=calendar.getTime();   //这个时间就是日期往后推一天的结果
            System.out.println(date1.toString());
            String fom2=sdf.format(date1);
            date1=sdf.parse(fom2);
            List<Tbhistory> list=  tbhistoryRepository.findAllByRecordTimeBetween(date1,date);
            this.mainService.transfer(list);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
