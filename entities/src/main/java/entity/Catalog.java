package entity;

import java.io.Serializable;
import java.util.List;

public class Catalog implements Serializable {

    private static final long serialVersionUID = 7990195023759147131L;

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
