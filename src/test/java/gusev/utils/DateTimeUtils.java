package gusev.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM d, yyyy hh:mm a", Locale.ENGLISH);

    public static String getCurrentDate(){
        return LocalDateTime.now().format(DATE_FORMATTER);
    }

    public static String getCurrentDateTime(){
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public static String getFutureDate(int days, int months, int years){
        return LocalDateTime.now().plusDays(days).plusMonths(months).plusYears(years).format(DATE_FORMATTER);
    }

    public static String getFutureDateTime(int days, int months, int years, int hours, int minutes){
        return LocalDateTime.now().plusDays(days).plusMonths(months).plusYears(years).plusHours(hours).plusMinutes(minutes).format(DATE_TIME_FORMATTER);
    }
}