package com.hexaware.util;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private static final String FILE_NAME = "Db.properties";

    // Private constructor to prevent instantiation
    private PropertyUtil() {
    }

    //  to get the connection string from the property file
    public static String getPropertyString() {
        Properties props = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(FILE_NAME);
            props.load(fis);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            return url + "?" + "user=" + username + "&password=" + password;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null; 
    }
}

