package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = TestConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("Файл config.properties не найден в src/test/resources");
            }
            PROPERTIES.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать config.properties", e);
        }
    }

    public static String getUiBaseUrl() {
        return get("uiBaseUrl");
    }

    public static String getApiBaseUrl() {
        return get("apiBaseUrl");
    }

    public static long getTimeout() {
        return Long.parseLong(get("timeout"));
    }

    public static String getLoggingMode() {
        return get("loggingMode");
    }

    public static String getUsername() {
        return get("username");
    }

    public static String getPassword() {
        return get("password");
    }

    public static String getStartProductName() {
        return get("startProductName");
    }

    public static String getStartProductPrice() {
        return get("startProductPrice");
    }

    public static void printConfig() {
        System.out.println("========== TEST CONFIG ==========");
        System.out.println("uiBaseUrl          = " + getUiBaseUrl());
        System.out.println("apiBaseUrl         = " + getApiBaseUrl());
        System.out.println("timeout            = " + getTimeout());
        System.out.println("loggingMode        = " + getLoggingMode());
        System.out.println("startProductName   = " + getStartProductName());
        System.out.println("startProductPrice  = " + getStartProductPrice());
        System.out.println("=================================");
    }

    private static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException("В config.properties нет ключа: " + key);
        }
        return value.trim();
    }
}