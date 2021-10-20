package com.kingdee.sqkg.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class getBeforeDate {
    private final static Logger logger = LoggerFactory.getLogger(getBeforeDate.class);
    public static String getSpecifiedDayBefore(String specifiedDay) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar c = Calendar.getInstance();

        Date date = null;

        try {

            date = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse(specifiedDay);

        } catch (Exception e) {
          logger.info(e.getMessage());
        }
        c.setTime(date);
        int mm = c.get(Calendar.DATE);
        c.set(Calendar.DATE, mm - 200);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayBefore;
    }
    public static String getSpecifiedDayBefore() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        calendar.set(Calendar.MINUTE,(calendar.get(Calendar.MINUTE) ));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String beforeDate = df.format(calendar.getTime());
        String lastDate = df.format(new Date());
        String  dayBefore= beforeDate + "~" + lastDate;
        return dayBefore;
    }


}
