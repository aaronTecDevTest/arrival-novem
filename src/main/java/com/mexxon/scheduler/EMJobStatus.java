package com.mexxon.scheduler;
/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 19.04.2016.
 * @since: 1.0
 * Package: com.mexxon.utilities
 */

public enum EMJobStatus {
    RUN,
    STOP,
    PAUSE,
    IDEAL;

    public static EMJobStatus fromString(String value){
        switch (value){
            case "run":
            case "Run":
            case "RUN":
                return RUN;
            case "stop":
            case "Stop":
            case "STOP":
                return STOP;
            case "pause":
            case "Pause":
            case "PAUSE":
                return PAUSE;
            case "ideal":
            case "Ideal":
            case "IDEAL":
                return IDEAL;
            default:
                return null;
        }
    }
}
