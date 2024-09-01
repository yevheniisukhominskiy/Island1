package com.eugened.entity.location;

import com.eugened.entity.object.organism.Organism;
import com.eugened.utils.GameContext;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Organism> organisms;

    public Cell() {
        this.organisms = new ArrayList<>();
    }

    public void addOrganism(Organism organism) {
        organisms.add(organism);
    }

    public void removeOrganism(Organism organism) {
        organisms.remove(organism);
    }

    public List<Organism> getOrganisms() {
        return organisms;
    }
}
