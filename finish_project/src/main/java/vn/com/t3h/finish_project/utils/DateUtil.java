package vn.com.t3h.finish_project.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil{

    public static final String FORMAT_DATE = "yyyy-mm-dd";

    public static Date convertStringToDate(String str,String formatDate) throws Exception {
        if (str == null) {
            log.error("date is null");
            return new Date();
        }
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertDateToString(Date date,String formatDate) throws Exception {
        if (date == null) {
            log.error("date is null");
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        return dateFormat.format(date);
    }
}
