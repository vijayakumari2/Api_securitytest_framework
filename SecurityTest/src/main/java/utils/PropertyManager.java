package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyManager {
    private Properties prop;
    private Properties envProp;
    public PropertyManager() {

        if (prop == null) {
            prop = new Properties();
            envProp = new Properties();
            try {
                File directoryPath = new File(System.getProperty("user.dir")+"/SecurityTest/src/test/resources/properties");
                if (directoryPath.exists() && directoryPath.isDirectory()) {
                    File[] filesList = directoryPath.listFiles();

                    if (filesList != null && filesList.length > 0) {
                        for (File file : filesList) {
                            FileInputStream propertyFiles = new FileInputStream(
                                    file);
                            prop.load(propertyFiles);
                        }
                    } else {
                        System.out.println("No property files found in the directory.");
                    }
                } else {
                    System.out.println("The specified properties directory does not exist.");
                }

                String env = prop.getProperty("env") + ".properties";
                if (env != null) {
                    File envFile = new File(directoryPath , env);
                    if (envFile.exists()) {
                        FileInputStream envPropertyFile = new FileInputStream(envFile);
                        envProp.load(envPropertyFile);
                    } else {
                        System.out.println("Environment-specific property file not found: " + env);
                    }
                } else {
                    System.out.println("No 'env' property specified in the main properties.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        return envProp.getProperty(key);
    }
}
