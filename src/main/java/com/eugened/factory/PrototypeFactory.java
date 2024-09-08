package com.eugened.factory;

import com.eugened.entity.object.organism.Organism;

public interface PrototypeFactory {
    public Organism create(Class<? extends Organism> type);
}
