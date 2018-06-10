package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FORESTS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"USER_ID", "FOREST_ID"})
        })
@NamedQuery(name = "getAllForests", query = "SELECT OBJECT(f) FROM Forest f")
public class Forest implements Serializable {
    private static final long serialVersionUID = -4758526350358053567L;
    private int forestId;

    private Integer height;
    private List<Elf> elvesByForestId;
    private User usersByUserId;

    @Id
    @Column(name = "FOREST_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getForestId() {
        return forestId;
    }

    public void setForestId(Integer forestId) {
        this.forestId = forestId;
    }

    @Basic
    @Column(name = "HEIGHT")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forest forest = (Forest) o;
        return Objects.equals(forestId, forest.forestId) &&
                Objects.equals(height, forest.height);
    }

    @Override
    public int hashCode() {

        return Objects.hash(forestId, height);
    }

    @OneToMany(mappedBy = "forestsByForestId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<Elf> getElvesByForestId() {
        return elvesByForestId;
    }

    public void setElvesByForestId(List<Elf> elvesByForestId) {
        this.elvesByForestId = elvesByForestId;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @Override
    public String toString() {
        return "Forest{" +
                "forestId=" + forestId +
                ", height=" + height +
                '}';
    }
}
