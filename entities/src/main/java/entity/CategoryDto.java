package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDto implements Serializable {
    private static final long serialVersionUID = 8209995818243468745L;

    private Integer height;
    private Integer categoryId;
    private List<ElementDto> elements;

    public static CategoryDto builder(Forest category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getForestId());
        categoryDto.setHeight(category.getHeight());
        List<Elf> elements = category.getElvesByForestId();
        categoryDto.elements = new ArrayList<>();
        elements.forEach(element -> categoryDto.elements.add(ElementDto.builder(element)));
        return categoryDto;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<ElementDto> getElements() {
        return elements;
    }

    public void setElements(List<ElementDto> elements) {
        this.elements = elements;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
