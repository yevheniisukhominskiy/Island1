package com.eugened.entity.object.organism.animal.herbivore.creatures;

import com.eugened.annotation.GameConfig;
import com.eugened.annotation.GameMember;
import com.eugened.entity.object.organism.animal.herbivore.Herbivore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@GameMember
@GameConfig(fileName = "config/entities/organisms/animals/herbivore/boar.yaml")
public class Boar extends Herbivore {
}
