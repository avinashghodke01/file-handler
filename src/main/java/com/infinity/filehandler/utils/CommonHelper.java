package com.infinity.filehandler.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonHelper {
    public static String getCurrentDate() {
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
