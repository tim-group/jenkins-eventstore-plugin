package org.jenkinsci.plugins.eventstore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class DateUtil {
    private DateUtil() { /* prevent instantiation */ }

    private static final SimpleDateFormat ISO_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    static {
        ISO_DATETIME_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static String epochToIsoDateString(long epochMillis) {
        return ISO_DATETIME_FORMAT.format(new Date(epochMillis));
    }
}
