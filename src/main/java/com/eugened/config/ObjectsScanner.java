package com.eugened.config;

import com.eugened.annotation.GameConfig;
import com.eugened.annotation.GameMember;
import com.eugened.entity.object.GameObject;
import com.eugened.entity.object.organism.Organism;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

public class ObjectsScanner {
    private static final Reflections reflections = new Reflections("com.eugened");
    private static ObjectsScanner instance;

    private ObjectsScanner() {

    }

    public static ObjectsScanner getInstance() {
        if (instance == null) {
            instance = new ObjectsScanner();
        }
        return instance;
    }

    public Set<Class<? extends Organism>> getAllGameObjectsClasses() {
        return reflections.getSubTypesOf(Organism.class).stream()
                .filter(c -> c.isAnnotationPresent(GameMember.class))
                .filter(c -> c.isAnnotationPresent(GameConfig.class))
                .collect(Collectors.toSet());
    }
}
