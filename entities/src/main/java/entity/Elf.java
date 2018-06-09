package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ELVES")
@NamedQuery(name = "getAllElves", query = "SELECT OBJECT(e) FROM Elf e")
public class Elf implements Serializable {

    private static final long serialVersionUID = 7773464502610941698L;
    private Integer elfId;
    private Integer arrowType;
    private Integer arrowsCount;
    private String name;
    private Integer power;
    private Forest forestsByForestId;

    public void setElfId(Integer elfId) {
        this.elfId = elfId;
    }

    @Id
    @Column(name = "ELF_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getElfId() {
        return elfId;
    }

    @Basic
    @Column(name = "ARROW_TYPE")
    public Integer getArrowType() {
        return arrowType;
    }

    public void setArrowType(Integer arrowType) {
        this.arrowType = arrowType;
    }

    @Basic
    @Column(name = "ARROWS_COUNT")
    public Integer getArrowsCount() {
        return arrowsCount;
    }

    public void setArrowsCount(Integer arrowsCount) {
        this.arrowsCount = arrowsCount;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "POWER")
    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elf that = (Elf) o;
        return elfId == that.elfId &&
                Objects.equals(arrowType, that.arrowType) &&
                Objects.equals(arrowsCount, that.arrowsCount) &&
                Objects.equals(name, that.name) &&
                Objects.equals(power, that.power);
    }

    @Override
    public int hashCode() {

        return Objects.hash(elfId, arrowType, arrowsCount, name, power);
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "FOREST_ID", referencedColumnName = "FOREST_ID")
    public Forest getForestsByForestId() {
        return forestsByForestId;
    }

    public void setForestsByForestId(Forest forestsByForestId) {
        this.forestsByForestId = forestsByForestId;
    }

    @Override
    public String toString() {
        return "Elf{" +
                "elfId=" + elfId +
                ", arrowType=" + arrowType +
                ", arrowsCount=" + arrowsCount +
                ", name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}
