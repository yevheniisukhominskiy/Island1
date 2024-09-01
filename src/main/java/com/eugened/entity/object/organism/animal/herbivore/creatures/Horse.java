package com.eugened.entity.object.organism.animal.herbivore.creatures;

import com.eugened.annotation.*;
import com.eugened.entity.object.organism.animal.herbivore.Herbivore;

@GameMember
public class Horse extends Herbivore {
    public Horse() {
        setSpeed(3); // TODO: Remake from num to method (parse from json)
    }
}
