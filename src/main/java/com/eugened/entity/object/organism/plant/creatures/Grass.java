package com.eugened.entity.object.organism.plant.creatures;

import com.eugened.annotation.*;
import com.eugened.entity.object.organism.plant.Plant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@GameMember
@GameConfig(fileName = "config/entities/organisms/plants/grass.yaml")
public class Grass extends Plant {

}
