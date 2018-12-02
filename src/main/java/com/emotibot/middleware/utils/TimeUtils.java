package com.emotibot.middleware.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtils
{
    public static int MILLISECOND_IN_DAY = 1000 * 60 * 60 * 24;
    
    public static long getServerlDayBeforeTimestamp(int days)
    {
        long timestamp = getTodayTimestamp() - ((long)days * MILLISECOND_IN_DAY);
        return timestamp;
    }
    
    public static long getTodayTimestamp()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    
    public static long getDayFirstTimestamp(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    
    public static Date getDateFromStr(String dateStr, String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<Date> getDateListFromStartAndEndTimestamp(long startTimestamp, long endTimestamp)
    {
        List<Date> ret = new ArrayList<Date>();
        for (long timestamp = startTimestamp; timestamp <= endTimestamp; timestamp += TimeUtils.MILLISECOND_IN_DAY)
        {
            ret.add(new Date(timestamp));
        }
        return ret;
    }
}
