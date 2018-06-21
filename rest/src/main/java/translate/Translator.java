package translate;

import entity.CategoryDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Translator {

    public void translate(CategoryDto categoryDto) {
        categoryDto.getElements().forEach(category -> category.setName("Content negotiaion - " + category.getName()));
    }
}