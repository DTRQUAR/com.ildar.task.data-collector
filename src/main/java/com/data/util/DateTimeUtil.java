package com.data.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public class DateTimeUtil {

    public static final DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TME_FORMATTER);
    }

}
