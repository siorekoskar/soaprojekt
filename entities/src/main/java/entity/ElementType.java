package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ELEMENT_TYPE", schema = "projekt", catalog = "")
public class ElementType implements Serializable {
    private static final long serialVersionUID = 2783439311230756108L;
    private Integer elementTypeId;
    private Integer nameLabel;
    private Integer intLabel1;
    private Integer powerLabel;
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
    @Column(name = "NAME_LABEL", nullable = true)
    public Integer getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Integer nameLabel) {
        this.nameLabel = nameLabel;
    }

    @Basic
    @Column(name = "INT_LABEL_1", nullable = true)
    public Integer getIntLabel1() {
        return intLabel1;
    }

    public void setIntLabel1(Integer intLabel1) {
        this.intLabel1 = intLabel1;
    }

    @Basic
    @Column(name = "POWER_LABEL", nullable = true)
    public Integer getPowerLabel() {
        return powerLabel;
    }

    public void setPowerLabel(Integer powerLabel) {
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
}
