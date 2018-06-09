package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FORESTS")
public class Forest implements Serializable {
    private static final long serialVersionUID = -4758526350358053567L;
    private int forestId;

    private Integer height;
    private Integer userId;
    private List<Elf> elvesByForestId;

    @Id
    @Column(name = "FOREST_ID", nullable = false)
    public Integer getForestId() {
        return forestId;
    }

    @Basic
    @Column(name = "HEIGHT")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Basic
    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forest that = (Forest) o;
        return forestId == that.forestId &&
                Objects.equals(height, that.height) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(forestId, height, userId);
    }

    @OneToMany(mappedBy = "forestsByForestId", fetch = FetchType.EAGER)
    public List<Elf> getElvesByForestId() {
        return elvesByForestId;
    }

    public void setElvesByForestId(List<Elf> elvesByForestId) {
        this.elvesByForestId = elvesByForestId;
    }

    public void setForestId(Integer forestId) {
        this.forestId = forestId;
    }
}
