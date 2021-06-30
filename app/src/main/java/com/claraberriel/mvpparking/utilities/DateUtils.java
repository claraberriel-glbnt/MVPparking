package com.claraberriel.mvpparking.utilities;

import java.util.Date;

public abstract class DateUtils {

    public static boolean isDateInTheFuture(Date date){
        return date.after(new Date());
    }

    public static boolean isEndDateBeforeStartDate(Date endDate, Date startDate) { return endDate.before(startDate); }
}
