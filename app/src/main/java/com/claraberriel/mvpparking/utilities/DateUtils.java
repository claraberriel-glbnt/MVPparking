package com.claraberriel.mvpparking.utilities;

import java.util.Date;

public class DateUtils {

    public boolean isDateInThePast(Date date){
        return date.before(new Date());
    }

    public boolean isEndDateBeforeStartDate(Date startDate, Date endDate) { return endDate.before(startDate); }
}
