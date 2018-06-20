package proj;

import entity.CategoryType;

import java.io.Serializable;

public class CategoryTypeDto implements Serializable {
    private static final long serialVersionUID = -6848846029390494765L;
    private String categoryName;
    private String categoryPropertyLabel;

    public CategoryTypeDto(String categoryName, String categoryPropertyLabel) {
        this.categoryName = categoryName;
        this.categoryPropertyLabel = categoryPropertyLabel;
    }

    public CategoryTypeDto() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPropertyLabel() {
        return categoryPropertyLabel;
    }

    public void setCategoryPropertyLabel(String categoryPropertyLabel) {
        this.categoryPropertyLabel = categoryPropertyLabel;
    }

    public static CategoryType categoryTypeBuilder(CategoryTypeDto categoryTypeDto){
        CategoryType categoryType = new CategoryType();
        categoryType.setCategoryName(categoryTypeDto.getCategoryName());
        categoryType.setCategoryPropertyLabel(categoryTypeDto.getCategoryPropertyLabel());
        return categoryType;
    }
}
