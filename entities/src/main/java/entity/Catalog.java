package entity;

import java.util.List;

public class Catalog {

    private Forest forest;
    private List<Elf> elves;

    public Catalog(Forest forest, List<Elf> elves) {
        this.forest = forest;
        this.elves = elves;
    }

    public Forest getForest() {
        return forest;
    }

    public void setForest(Forest forest) {
        this.forest = forest;
    }

    public List<Elf> getElves() {
        return elves;
    }

    public void setElves(List<Elf> elves) {
        this.elves = elves;
    }
}
