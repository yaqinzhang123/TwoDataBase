package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.first.Tbhistory;
import com.example.accessingdatamysql.second.DAT_HouseHoldData;
import com.example.accessingdatamysql.second.DAT_HouseHoldDataRepository;
import com.example.accessingdatamysql.second.DAT_RoomTemp;
import com.example.accessingdatamysql.second.DAT_RoomTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MainService {
//    @Autowired
//    private DAT_HouseHoldDataRepository dat_houseHoldDataRepository;

    @Autowired
    private DAT_RoomTempRepository dat_roomTempRepository;

//    public List<DAT_HouseHoldData> transfer(List<Tbhistory> tbhistorys){
//        if(tbhistorys==null||tbhistorys.size()==0)
//            return null;
//        List<DAT_HouseHoldData> datas=new ArrayList<>();
//        for (Tbhistory tbhistory:tbhistorys
//             ) {
//            DAT_HouseHoldData dat_houseHoldData =dat_houseHoldDataRepository.findByHouseHoldIDAndRoomTempAndGetTime(tbhistory.getDeviceName(),tbhistory.getTem(),tbhistory.getRecordTime());
//            if(dat_houseHoldData ==null){
//                DAT_HouseHoldData data=new DAT_HouseHoldData();
//                data.setHouseHoldID(tbhistory.getDeviceName());
//                data.setRoomTemp(tbhistory.getTem());
//                data.setGetTime(tbhistory.getRecordTime());
//                data.setAddTime(tbhistory.getRecordTime());
//                datas.add(data);
//            }
//        }
//        if(datas==null||datas.size()==0)
//            return null;
//        this.dat_houseHoldDataRepository.saveAll(datas);
//        return datas;
//    }
    public List<DAT_RoomTemp> transfer(List<Tbhistory> tbhistorys, Date date) {
        List<DAT_RoomTemp> datas=new ArrayList<DAT_RoomTemp>();
        try{
        if(tbhistorys==null||tbhistorys.size()==0)
            return null;
        System.out.print("======="+tbhistorys.size()+"======");
        for (Tbhistory tbhistory:tbhistorys)
        {
            Calendar calendar   =   new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.MINUTE,5);
            Date date1=calendar.getTime();
            if(tbhistory.getRecordTime().before(date1)){
                DAT_RoomTemp dat_roomTemp =dat_roomTempRepository.findByRoomTempPointIDAndRoomTempAndGetTime(tbhistory.getDeviceName(),tbhistory.getTem(),tbhistory.getRecordTime());
                if(dat_roomTemp !=null) {
                    continue;
                }
            }
            DAT_RoomTemp data=new DAT_RoomTemp();
            data.setRoomTempPointID(tbhistory.getDeviceName());
            data.setRoomTemp(tbhistory.getTem());
            data.setGetTime(tbhistory.getRecordTime());
            data.setAddTime(tbhistory.getRecordTime());
            datas.add(data);

        }
        if(datas==null||datas.size()==0)
            return null;
        this.dat_roomTempRepository.saveAll(datas);
        }catch (Exception e){
           e.printStackTrace();
        }
        return datas;
    }
    public List<DAT_RoomTemp> transfer1(List<Tbhistory> tbhistorys) {
        List<DAT_RoomTemp> datas=new ArrayList<DAT_RoomTemp>();
        try{
            if(tbhistorys==null||tbhistorys.size()==0)
                return null;
            System.out.print("======="+tbhistorys.size()+"======");
            for (Tbhistory tbhistory:tbhistorys)
            {
//            DAT_RoomTemp dat_roomTemp =dat_roomTempRepository.findByRoomTempPointIDAndRoomTempAndGetTime(tbhistory.getDeviceName(),tbhistory.getTem(),tbhistory.getRecordTime());
//            if(dat_roomTemp ==null){
//                System.out.print(dat_roomTemp);
                DAT_RoomTemp data=new DAT_RoomTemp();
                data.setRoomTempPointID(tbhistory.getDeviceName());
                data.setRoomTemp(tbhistory.getTem());
                data.setGetTime(tbhistory.getRecordTime());
                data.setAddTime(tbhistory.getRecordTime());
                datas.add(data);
//            }
            }
//            if(datas==null||datas.size()==0)
//                return null;
            this.dat_roomTempRepository.saveAll(datas);
        }catch (Exception e){
            e.printStackTrace();
        }
        return datas;
    }
    //全部去重
    public List<DAT_RoomTemp> transferlist(List<Tbhistory> tbhistorys) {
        List<DAT_RoomTemp> datas=new ArrayList<DAT_RoomTemp>();
        try{
            if(tbhistorys==null||tbhistorys.size()==0)
                return null;
            System.out.print("======="+tbhistorys.size()+"======");
            for (Tbhistory tbhistory:tbhistorys)
            {
                DAT_RoomTemp dat_roomTemp =dat_roomTempRepository.findByRoomTempPointIDAndRoomTempAndGetTime(tbhistory.getDeviceName(),tbhistory.getTem(),tbhistory.getRecordTime());
                if(dat_roomTemp !=null) {
                    continue;
                }
                DAT_RoomTemp data=new DAT_RoomTemp();
                data.setRoomTempPointID(tbhistory.getDeviceName());
                data.setRoomTemp(tbhistory.getTem());
                data.setGetTime(tbhistory.getRecordTime());
                data.setAddTime(tbhistory.getRecordTime());
                datas.add(data);

            }
            if(datas==null||datas.size()==0)
                return null;
            this.dat_roomTempRepository.saveAll(datas);
        }catch (Exception e){
            e.printStackTrace();
        }
        return datas;
    }
}
