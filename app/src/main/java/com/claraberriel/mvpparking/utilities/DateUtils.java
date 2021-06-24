package com.claraberriel.mvpparking.utilities;

import java.util.Date;

public class DateUtils {
    public static boolean isDateInTheFuture(Date date){
        return date.after(new Date());
    }
}
