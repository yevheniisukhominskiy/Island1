package com.eugened.entity.object.organism.animal;

import com.eugened.entity.object.ability.Edible;
import com.eugened.entity.object.ability.Movable;
import com.eugened.entity.object.ability.Reproducible;
import com.eugened.entity.object.organism.Organism;

public abstract class Animal extends Organism implements Edible, Movable, Reproducible {
    @Override
    public void play() {
        // TODO: Develop the life cycle of an object
    }

    @Override
    public void eat() {
        // TODO: Implements ability void eat()
    }

    @Override
    public void move() {
        // TODO: Implements ability void move()
    }

    @Override
    public Organism reproduce() {
        // TODO: Implements ability Organism reproduce()
        return null;
    }
}
