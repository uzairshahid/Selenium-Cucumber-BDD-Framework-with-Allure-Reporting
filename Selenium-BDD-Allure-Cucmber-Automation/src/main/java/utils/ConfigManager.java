package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class ConfigManager {

    private static final JsonNode configRoot;
    private static final String env;

    static {
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.json")) {
            if (is == null) {
                throw new RuntimeException("config.json not found");
            }
            ObjectMapper mapper = new ObjectMapper();
            configRoot = mapper.readTree(is);

            String systemEnv = System.getProperty("env");
            env = (systemEnv != null) ? systemEnv : configRoot.get("environment").asText();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.json", e);
        }
    }

    private static JsonNode getEnvNode() {
        return configRoot.get(env);
    }

    public static String getBaseUrl() {
        return getEnvNode().get("baseUrl").asText();
    }

    public static String getBrowser() {
        return getEnvNode().get("browser").asText();
    }

    public static String getEnvironment() {
        return env;
    }
}
