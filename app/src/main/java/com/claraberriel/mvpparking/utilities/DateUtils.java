package com.claraberriel.mvpparking.utilities;

import java.util.Date;

public abstract class DateUtils {

    public static boolean isDateInThePast(Date date){
        return date.before(new Date());
    }

    public static boolean isEndDateBeforeStartDate(Date startDate, Date endDate) { return endDate.before(startDate); }
}
