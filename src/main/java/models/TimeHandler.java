package models;

import java.time.LocalDateTime;

public abstract class TimeHandler {
    private static final int minute = LocalDateTime.now().getMinute();
    private static final int hour = LocalDateTime.now().getHour();
    private static final int day = LocalDateTime.now().getDayOfMonth();
    private static final int month = LocalDateTime.now().getMonthValue();
    private static final int year = LocalDateTime.now().getYear();

    private static String convertMinute() {
        if(minute < 10) {
            return "0" + minute;
        } else {
            return String.valueOf(minute);
        }
    }

    private static String convertHour() {
        if(hour < 10) {
            return "0" + hour;
        } else {
            return String.valueOf(hour);
        }
    }

    private static String convertDay() {
        if(day < 10) {
            return "0" + day;
        } else {
            return String.valueOf(day);
        }
    }

    private static String convertMonth() {
        if(month < 10) {
            return "0" + month;
        } else {
            return String.valueOf(month);
        }
    }

    public static String getDate() {
        return convertDay() + "." + convertMonth() + "." + year;
    }

    public static String getTime() {
        return convertHour() + ":" + convertMinute();
    }
}
