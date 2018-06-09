package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USERS", schema = "projekt")
public class User {
    private Integer userId;
    private List<Forest> forestsByUserId;

    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }

    @OneToMany(mappedBy = "usersByUserId")
    public List<Forest> getForestsByUserId() {
        return forestsByUserId;
    }

    public void setForestsByUserId(List<Forest> forestsByUserId) {
        this.forestsByUserId = forestsByUserId;
    }
}
