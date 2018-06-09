package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ELVES")
public class Elf implements Serializable {

    private static final long serialVersionUID = -1447366905522562997L;

    private int elfId;
    private String name;
    private int arrowsCount;
    private int arrowType;
    private int power;
    private int forestId;

    public Elf(String name, int forestId) {
        this.name = name;
        this.forestId = forestId;
    }

    public Elf() {
    }

    @Id
    @GeneratedValue
    @Column(name = "ELF_ID")
    public int getElfId() {
        return elfId;
    }

    public void setElfId(int elfId) {
        this.elfId = elfId;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ARROWS_COUNT")
    public int getArrowsCount() {
        return arrowsCount;
    }

    public void setArrowsCount(int arrowsCount) {
        this.arrowsCount = arrowsCount;
    }

    @Column(name = "ARROW_TYPE")
    public int getArrowType() {
        return arrowType;
    }

    public void setArrowType(int arrowType) {
        this.arrowType = arrowType;
    }

    @Column(name = "POWER")
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Column(name = "FOREST_ID")
    public int getForestId() {
        return forestId;
    }

    public void setForestId(int forestId) {
        this.forestId = forestId;
    }

    @Override
    public String toString() {
        return String.format("Elf %s: arrows count: %d, arrows type: %s, power: %d", name, arrowsCount, arrowType, power);
    }
}
