package com.data.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public class DateTimeUtil {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String toString(LocalDateTime dateTime) {
        return dateTime == null ? "" : dateTime.format(DATE_TIME_FORMATTER);
    }

}
