package proj;


import entity.Catalog;
import entity.Elf;
import entity.Forest;

import java.util.List;

public interface RemoteCatalogue {
    List<Catalog> getCatalogs();
    List<Forest> getForests();
    List<Elf> getElves();
    void addForest(Integer height);
    void addElf(String elfName, Forest forestId);
}
