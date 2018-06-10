package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ELEMENT_TYPE")
public class ElementType implements Serializable {
    private static final long serialVersionUID = 2783439311230756108L;
    private Integer elementTypeId;
    private String nameLabel;
    private String intLabel1;
    private String intLabel2;
    private String powerLabel;
    private List<Elf> elvesByElementTypeId;

    @Id
    @Column(name = "ELEMENT_TYPE_ID", nullable = false)
    public Integer getElementTypeId() {
        return elementTypeId;
    }

    public void setElementTypeId(Integer elementTypeId) {
        this.elementTypeId = elementTypeId;
    }

    @Basic
    @Column(name = "NAME_LABEL", nullable = true, length = 255)
    public String getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel = nameLabel;
    }

    @Basic
    @Column(name = "INT_LABEL_1", nullable = true)
    public String getIntLabel1() {
        return intLabel1;
    }

    public void setIntLabel1(String intLabel1) {
        this.intLabel1 = intLabel1;
    }

    @Basic
    @Column(name = "INT_LABEL_2", nullable = true)
    public String getIntLabel2() {
        return intLabel2;
    }

    public void setIntLabel2(String intLabel2) {
        this.intLabel2 = intLabel2;
    }

    @Basic
    @Column(name = "POWER_LABEL", nullable = true)
    public String getPowerLabel() {
        return powerLabel;
    }

    public void setPowerLabel(String powerLabel) {
        this.powerLabel = powerLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementType that = (ElementType) o;
        return Objects.equals(elementTypeId, that.elementTypeId) &&
                Objects.equals(nameLabel, that.nameLabel) &&
                Objects.equals(intLabel1, that.intLabel1) &&
                Objects.equals(powerLabel, that.powerLabel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(elementTypeId, nameLabel, intLabel1, powerLabel);
    }

    @OneToMany(mappedBy = "elementTypeByElementTypeId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<Elf> getElvesByElementTypeId() {
        return elvesByElementTypeId;
    }

    public void setElvesByElementTypeId(List<Elf> elvesByElementTypeId) {
        this.elvesByElementTypeId = elvesByElementTypeId;
    }

    @Override
    public String toString() {
        return "ElementType{" +
                "nameLabel='" + nameLabel + '\'' +
                ", intLabel1='" + intLabel1 + '\'' +
                ", intLabel2='" + intLabel2 + '\'' +
                ", powerLabel='" + powerLabel + '\'' +
                '}';
    }
}
