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
            case "Import":
            case "IMPORT":
                return IMPORT;
            case "export":
            case "Export":
            case "EXPORT":
                return EXPORT;
            case "import_sql":
            case "Import_Sql":
            case "IMPORT_SQL":
                return IMPORT_SQL;
            case "export_sql":
            case "Export_Sql":
            case "EXPOR_SQL":
                return EXPORT_SQL;
            case "export_mexxon_csv":
            case "Export_Mexxon_Csv":
            case "EXPORT_MEXXON_CSV":
                return EXPORT_MEXXON_CSV;
            default:
                return null;
        }
    }
}
