package proj;


import entity.Catalog;
import entity.Forest;

import java.util.List;

public interface RemoteCatalogue {
    List<Catalog> getCatalogs();
    List<Forest> getForests();
    void addForest(Integer height);
    void addElf(String elfName, Forest forestId);
}
