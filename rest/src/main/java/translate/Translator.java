package translate;

import entity.CategoryDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class Translator {

    public void translate(Locale locale, List<CategoryDto> categoryDtos) {
        if (locale.equals(Locale.GERMAN)) {
            categoryDtos.forEach(this::translate);
        }
    }

    private void translate(CategoryDto categoryDto) {
        categoryDto.getElements().forEach(category -> category.setName("Content negotiaion - " + category.getName()));
    }
}