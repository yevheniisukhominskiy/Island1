package com.eugened.config;

import com.eugened.annotation.GameConfig;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.net.URL;

public class PrototypeLoader {
    private static PrototypeLoader instance;
    public static PrototypeLoader getInstance() {
        if (instance == null) {
            instance = new PrototypeLoader();
        }
        return instance;
    }

    private PrototypeLoader() {

    }

    public <T> T loadPrototype(@NotNull Class<T> type) {
        if (!type.isAnnotationPresent(GameConfig.class)) {
            throw new IllegalArgumentException("Class " + type + " is not annotated with @GameConfig");
        }
        return loadPrototype(getConfigFilePath(type), type);
    }

    private <T> T loadPrototype(@NotNull URL configFilePath, Class<T> type) {
        YAMLMapper mapper = new YAMLMapper();
        T organism;

        try {
            organism = mapper.readValue(configFilePath, type);
        } catch (IOException e) {
            String message = String.format("Failed to load config file %s", configFilePath);
            throw new IllegalArgumentException(message, e);
        }
        return organism;
    }

    private URL getConfigFilePath(@NotNull Class<?> type) {
        GameConfig config = type.getAnnotation(GameConfig.class);
        return type.getClassLoader().getResource(config.fileName());
    }
}
