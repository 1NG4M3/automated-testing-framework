package gusev.utils;

import gusev.constants.PropertyConstants;
import java.io.FileInputStream;
import java.util.Properties;
import static gusev.constants.PropertyConstants.CONFIG;

public class PropertyManager {
    public static String propHandler(PropertyConstants file, String key) {
        FileInputStream props = null;
        Properties property = new Properties();
        try {
            if (file.equals(CONFIG)) {
                props = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            }
            property.load(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property.getProperty(key);
    }
}