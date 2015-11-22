package com.zaharovs.weatherapp.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class WD {
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private String dateValue;
    private Calendar calendar;

    public WD(String dateValue) {
        this.dateValue = dateValue;
        processingDate();
    }

    private void processingDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        this.calendar = Calendar.getInstance();
        Date date = null;

        try {
            date = simpleDateFormat.parse(this.dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.calendar.setTime(date);
    }

    public String getDayOfMonth() {
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public String getDayOfWeek() {
        return String.valueOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
    }

    public String getMonth() {
        return String.valueOf(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));
    }

    public String getTime() {
        Formatter formatter = new Formatter();
        formatter.format("%tH:%tM", calendar, calendar);
        return formatter.toString();
    }

}
