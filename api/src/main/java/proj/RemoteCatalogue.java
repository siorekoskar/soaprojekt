package proj;


import entity.Catalog;
import entity.Elf;
import entity.Forest;
import entity.User;

import java.util.List;

public interface RemoteCatalogue {
    List<Catalog> getCatalogs();
    List<Forest> getForests();
    List<Elf> getElves();
    void addForest(Integer height);
    void addElf(Elf elfName);
    void removeElf(Elf elf);
    void removeForest(Forest forest);
    List<User> getUsers();

}
