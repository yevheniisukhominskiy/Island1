package com.eugened.entity.object.organism.animal.predator.creatures;

import com.eugened.annotation.*;
import com.eugened.entity.object.organism.Organism;
import com.eugened.entity.object.organism.animal.Animal;
import com.eugened.entity.object.organism.animal.herbivore.creatures.Horse;
import com.eugened.entity.object.organism.animal.predator.Predator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@GameMember
@GameConfig(fileName = "config/entities/organisms/animals/predator/wolf.yaml")
public class Wolf extends Predator {
}
