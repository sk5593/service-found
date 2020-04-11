package com.service.found.util;

import java.text.SimpleDateFormat;

public class TimeUtil {
    public static String formatDistanceTime(long createTime){
        long distanceTime = (System.currentTimeMillis()-createTime)/1000;
        if (distanceTime < 60){
            return (distanceTime+"秒前");
        }else if (distanceTime < 3600){
            return ((distanceTime/60)+"分钟前");
        }else if (distanceTime < 86400){
            return ((distanceTime/3600)+"小时前");
        }else if (distanceTime < 604800){
            return ((distanceTime/86400)+"天前");
        }else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(distanceTime);
            return(format);
        }
    }
}
