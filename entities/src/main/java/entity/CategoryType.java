package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY_TYPE")
public class CategoryType implements Serializable {
    private static final long serialVersionUID = 8334826655962833011L;
    private Integer categoryTypeId;
    private String categoryName;
    private String categoryPropertyLabel;
    private List<Forest> forestsByCategoryTypeId;

    @Id
    @Column(name = "CATEGORY_TYPE_ID", nullable = false)
    public Integer getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(Integer categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    @Basic
    @Column(name = "CATEGORY_NAME", nullable = true, length = 255)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "CATEGORY_PROPERTY_LABEL", nullable = true, length = 255)
    public String getCategoryPropertyLabel() {
        return categoryPropertyLabel;
    }

    public void setCategoryPropertyLabel(String categoryPropertyLabel) {
        this.categoryPropertyLabel = categoryPropertyLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryType that = (CategoryType) o;
        return Objects.equals(categoryTypeId, that.categoryTypeId) &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(categoryPropertyLabel, that.categoryPropertyLabel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryTypeId, categoryName, categoryPropertyLabel);
    }

    @OneToMany(mappedBy = "categoryTypeByCategoryTypeId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<Forest> getForestsByCategoryTypeId() {
        return forestsByCategoryTypeId;
    }

    public void setForestsByCategoryTypeId(List<Forest> forestsByCategoryTypeId) {
        this.forestsByCategoryTypeId = forestsByCategoryTypeId;
    }

    @Override
    public String toString() {
        return "CategoryType{" +
                "categoryName='" + categoryName + '\'' +
                ", categoryPropertyLabel='" + categoryPropertyLabel + '\'' +
                '}';
    }
}
