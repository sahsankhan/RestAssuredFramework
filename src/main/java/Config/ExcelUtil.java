package Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    public static class EXCEL_CONSTANTS {
        public static final String CUSTOMER = "CUSTOMER";
        public static final String COUNTRY = "COUNTRY";
        public static final String SITE = "SITE";

    }

    public static class API_CONSTANTS {
        public static final String CUSTOMER = "customer";
        public static final String COUNTRY = "country";
        public static final String SITE = "site";
    }

    public static final Map<String, String> KEY_MAPPER = new HashMap();
    public static final List<String> EXCEL_MASTER_KEYS = new ArrayList<>();

    static {
        KEY_MAPPER.put(EXCEL_CONSTANTS.COUNTRY, API_CONSTANTS.COUNTRY);
        KEY_MAPPER.put(EXCEL_CONSTANTS.SITE, API_CONSTANTS.SITE);
    }

    static {
        EXCEL_MASTER_KEYS.add(EXCEL_CONSTANTS.COUNTRY);
        EXCEL_MASTER_KEYS.add(EXCEL_CONSTANTS.SITE);
    }


}
