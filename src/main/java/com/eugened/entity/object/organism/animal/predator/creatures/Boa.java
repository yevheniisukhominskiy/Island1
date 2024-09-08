package com.eugened.entity.object.organism.animal.predator.creatures;

import com.eugened.annotation.GameConfig;
import com.eugened.annotation.GameMember;
import com.eugened.entity.object.organism.animal.predator.Predator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@GameMember
@GameConfig(fileName = "config/entities/organisms/animals/predator/boa.yaml")
public class Boa extends Predator {

}
