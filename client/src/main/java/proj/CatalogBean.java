package proj;

import entity.Catalog;
import entity.Elf;
import entity.Forest;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CatalogBean implements Serializable {

    private static final long serialVersionUID = 1524342261421310222L;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    private Forest currentForest;

    public void sendElf(String elfName) {
        remoteCatalogue.addElf(elfName, currentForest);
    }

    public List<Catalog> getCatalogs() {
        return remoteCatalogue.getCatalogs();
    }

    public List<Forest> getForests() {
        return remoteCatalogue.getForests();
    }

    public List<Elf> getElves() {
        return remoteCatalogue.getElves();
    }

    public void addForest(Integer height) {
        remoteCatalogue.addForest(height);
    }

    public void setCurrentForest(Forest f) {
        currentForest = f;
    }

    public Forest getCurrentForest() {
        return currentForest;
    }
}
