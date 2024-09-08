package com.eugened.factory;

import com.eugened.entity.object.organism.Organism;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrganismPrototypeFactory implements PrototypeFactory {
    private final Map<Class<? extends Organism>, Organism> prototypes = new HashMap<>();

    private static OrganismPrototypeFactory instance;

    private OrganismPrototypeFactory() {

    }

    public static OrganismPrototypeFactory getInstance() {
        if (instance == null) {
            instance = new OrganismPrototypeFactory();
        }
        return instance;
    }

    public void registerPrototype(Organism prototype) {
        prototypes.put(prototype.getClass(), prototype);
    }

    @Override
    public Organism create(Class<? extends Organism> type) {
        if (!prototypes.containsKey(type)) {
            throw new IllegalArgumentException("Unknown organism type: " + type);
        }
        Organism prototype = prototypes.get(type);
        return prototype.createInstance();
    }

    public Collection<Organism> getAllPrototypes() {
        return prototypes.values();
    }
}
