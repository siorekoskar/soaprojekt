package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FORESTS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"USER_ID", "FOREST_ID"})
        })
public class Forest implements Serializable {

    private static final long serialVersionUID = 422125652623546242L;

    private int forestId;
    private int height;
    private Integer userId;

    public Forest(int height) {
        this.height = height;
    }

    public Forest() {
    }

    @Id
    @GeneratedValue
    @Column(name = "FOREST_ID")
    public int getForestId() {
        return forestId;
    }

    public void setForestId(int categoryId) {
        this.forestId = categoryId;
    }

    @Column(name = "HEIGHT")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("Forest %d: height: %d", forestId, height);
    }
}
