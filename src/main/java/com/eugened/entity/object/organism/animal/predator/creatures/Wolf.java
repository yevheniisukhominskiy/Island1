package com.eugened.entity.object.organism.animal.predator.creatures;

import com.eugened.annotation.*;
import com.eugened.entity.object.organism.animal.Animal;
import com.eugened.entity.object.organism.animal.herbivore.creatures.Horse;
import com.eugened.entity.object.organism.animal.predator.Predator;

import java.util.HashMap;
import java.util.Map;

@GameMember
public class Wolf extends Predator {

    public Wolf() {
        setEatingProbability(Horse.class, 50.0); // TODO: Remake from num to method (parse from json)
        setSpeed(2); // TODO: Remake from num to method (parse from json)
    }
}
