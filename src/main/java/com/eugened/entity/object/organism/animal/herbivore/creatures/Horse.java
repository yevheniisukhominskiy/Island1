package com.eugened.entity.object.organism.animal.herbivore.creatures;

import com.eugened.annotation.*;
import com.eugened.entity.object.organism.animal.herbivore.Herbivore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@GameMember
@GameConfig(fileName = "config/entities/organisms/animals/herbivore/horse.yaml")
public class Horse extends Herbivore {

}
