package proj;


import entity.*;

import java.util.List;

public interface RemoteCatalogue {
    List<Forest> getForests();
    List<Elf> getElves();
    void addForest(Forest category);
    void addElf(Elf elfName);
    void removeElf(Elf elf);
    void removeForest(Forest forest);
    List<User> getUsers();
    boolean changePassword(UserDetails userDetails);
    List<CategoryType> getCategoryTypes();
    List<ElementType> getElementTypes();
}
