package proj;


import entity.*;

import java.util.List;

public interface RemoteCatalogue {
    List<Forest> getForests();
    List<Elf> getElves();
    void addForest(Forest category);
    int addElf(Elf elfName);
    int changeVariables(Elf element);
    void removeElf(Elf elf);
    void removeForest(Forest forest);
    List<User> getUsers();
    boolean changePassword(UserDetails userDetails);
    List<CategoryType> getCategoryTypes();
    List<ElementType> getElementTypes();
    Integer updateElement(Elf element);

    void sendCategoryType(CategoryTypeDto categoryTypeDto);
    void sendElementType(ElementTypeDto elementTypeDto);
    boolean modifyLabel(ElementTypeDto elementType);
}
