package com.mexxon.scheduler;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 13.04.2016.
 * @since: 1.0
 * Package: com.mexxon.utilities
 */
public enum EMSchaduler {
    EVERY_MIN,
    EVERY_HOUR,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY;

    public static EMSchaduler fromString(String values) {
        switch (values) {
            case "every_min":
            case "Every_Min":
            case "EVERY_MIN":
                return EVERY_MIN;
            case "every_hour":
            case "Every_Hour":
            case "EVERY_HOUR":
                return EVERY_HOUR;
            case "daily":
            case "Daily":
            case "DAILY":
                return DAILY;
            case "weekly":
            case "Weekly":
            case "WEEKLY":
                return WEEKLY;
            case "monthly":
            case "Monthly":
            case "MONTHLY":
                return MONTHLY;
            case "yearly":
            case "Yearly":
            case "YEARLY":
                return YEARLY;
            default:
                return null;
        }
    }
}
