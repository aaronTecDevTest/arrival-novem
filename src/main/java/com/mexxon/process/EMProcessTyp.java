package com.mexxon.process;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 13.04.2016.
 * @since: 1.0
 * Package: com.mexxon.utilities
 */
public enum EMProcessTyp {
    IMPORT,
    EXPORT,
    IMPORT_SQL,
    EXPORT_SQL,
    EXPORT_MEXXON_CSV;

   public static EMProcessTyp formString(String value) {
        switch (value) {
            case "import":
                return IMPORT;
            case "export":
                return EXPORT;
            case "import_sql":
                return IMPORT_SQL;
            case "export_sql":
                return EXPORT_SQL;
            case "export_mexxon_csv":
                return EXPORT_MEXXON_CSV;
        }
        return null;
    }
}
