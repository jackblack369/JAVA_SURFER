package com.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dongwei
 * 2018/8/22 9:28
 **/
public class TimeTest {

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    public static long dateToStamp2(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static void main(String[] args) throws ParseException{
        long time1 = dateToStamp2("2018-08-22 09:20:20");
        long time2 = dateToStamp2("2018-08-22 09:22:20");
        Long s = (time2 - time1) / (1000 * 60);
        System.out.println(s);

        String resultStamp=stampToDate("150512533500");
        System.out.println(resultStamp);

        System.out.println("current stamp:"+System.currentTimeMillis());

        System.out.println("require stamp:"+dateToStamp2("2018-08-23 11:40:20"));

    }
}
