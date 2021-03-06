package com.example.accessingdatamysql;

import com.example.accessingdatamysql.first.Tbhistory;
import com.example.accessingdatamysql.first.TbhistoryRepository;
import com.example.accessingdatamysql.second.DAT_HouseHoldData;
import com.example.accessingdatamysql.second.DAT_HouseHoldDataRepository;
import com.example.accessingdatamysql.second.DAT_RoomTemp;
import com.example.accessingdatamysql.second.DAT_RoomTempRepository;
import com.example.accessingdatamysql.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author caiwl
 * @date 2020/4/3 8:52
 */
@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private TbhistoryRepository tbhistoryRepository;
//    @Autowired
//    private DAT_HouseHoldDataRepository dat_houseHoldDataRepository;
    @Autowired
    private DAT_RoomTempRepository dat_roomTempRepository;
    @Autowired
    private MainService mainService;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @GetMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam String name, @RequestParam Double tem) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Tbhistory n = new Tbhistory();

        n.setDeviceName(name);
        n.setTem(tem);
        n.setRecordTime(new Date());
        tbhistoryRepository.save(n);
        return "Saved";
    }
    @GetMapping("/rkmonitor/getList")
    public @ResponseBody Object getList(){
        return  tbhistoryRepository.findAll();
    }
    @GetMapping("/UserDatabase/getList")
    public @ResponseBody Object getListU(){
        return  dat_roomTempRepository.findAll();
    }
    @GetMapping("/first")
    public @ResponseBody Object first(){
            try{
                Date date=new   Date();//取时间
                System.out.println(date.toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fom1=sdf.format(date);
                date=sdf.parse(fom1);
                List<Tbhistory> list=  tbhistoryRepository.findAllByRecordTimeBefore(date);
                List<DAT_RoomTemp> datas=this.mainService.transferlist(list);
                if(datas==null){
                    logger.info("first transfer is null!");
                    return "first transfer is null!" ;
                }
                return datas;
            }catch (Exception e){
                logger.error(e.toString());
                System.out.println(e);
                return e.toString();
            }

    }

    @GetMapping("/timing")
    public @ResponseBody Object timing(){
        try{
            Date date=new   Date();//取时间
            System.out.println(date.toString());
            Calendar calendar   =   new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
            Date date1=calendar.getTime();   //这个时间就是日期往后推一天的结果
            System.out.println(date1.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fom1=sdf.format(date1);
            date1=sdf.parse(fom1);
            List<Tbhistory> list=  tbhistoryRepository.findAllByRecordTimeBetween(date1,date);
            List<DAT_RoomTemp> datas=this.mainService.transfer(list,date1);
            if(datas==null){
                logger.info("timing first transfer is null!");
                return "timing transfer is null!" ;
            }
            return datas;
        }catch (Exception e){
            logger.info(e.toString());
            return e.toString();
        }

    }
    @GetMapping("/hours")
    public @ResponseBody Object hours(){
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
            List<Tbhistory> list=  tbhistoryRepository.queryTimeBetween(date1,date);
            List<DAT_RoomTemp> datas=this.mainService.transfer(list,date1);
            if(datas==null){
                logger.info("hours first transfer is null!");
                return "hours transfer is null!" ;
            }
            return datas;
        }catch (Exception e){
            logger.info(e.toString());
            return e.toString();
        }
    }
    @GetMapping("/hourtest")
    public @ResponseBody Object hours1(int i){
        try{
            Date date=new   Date();//取时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
            String fom1=sdf.format(date);
            date=sdf.parse(fom1);
            System.out.println(date.toString());
            Calendar calendar   =   new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.MINUTE,i);
            Date date1=calendar.getTime();   //这个时间就是日期往后推一天的结果
            System.out.println(date1.toString());
            String fom2=sdf.format(date1);
            date1=sdf.parse(fom2);
            List<Tbhistory> list=  tbhistoryRepository.queryTimeBetween(date1,date);
            List<DAT_RoomTemp> datas=this.mainService.transfer(list,date1);
            if(datas==null){
                logger.info("hours1 first transfer is null!");
                return "hours1 transfer is null!" ;
            }
            return datas;
        }catch (Exception e){
            logger.info(e.toString());
            return e.toString();
        }
    }
    @GetMapping("/timingDate")
    public @ResponseBody Object timing(String datetime,String  datetime1){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1=sdf.parse(datetime);
            Date date=sdf.parse(datetime1);
            System.out.println(date.toString());
            System.out.println(date1.toString());
            List<Tbhistory> list=  tbhistoryRepository.queryTimeBetween(date1,date);
            List<DAT_RoomTemp> datas=this.mainService.transfer(list,date1);
            if(datas==null){
                logger.info("timingDate first transfer is null!");
                return "timingDate transfer is null!" ;
            }
            return datas;
        }catch (Exception e){
            logger.info(e.toString());
            return e.toString();
        }

    }
    @GetMapping("/deleteDAT")
    public @ResponseBody Object deleteDAT(String datetime) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=sdf.parse(datetime);
            logger.info(date.toString());
            return this.mainService.deleteDat(date);
        }catch (Exception e){
            logger.info(e.toString());
            return e.toString();
        }

    }
    @GetMapping("/deleteTBHistory")
    public @ResponseBody Object deleteTBHistory(String datetime) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=sdf.parse(datetime);
            logger.info(date.toString());
            return this.mainService.deleteTBHistory(date);
        }catch (Exception e){
            logger.info(e.toString());
            return e.toString();
        }

    }
}
