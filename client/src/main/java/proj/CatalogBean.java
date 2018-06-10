package proj;

import entity.CategoryType;
import entity.Elf;
import entity.Forest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ConversationScoped
@Named
public class CatalogBean implements Serializable {

    private static final long serialVersionUID = 1524342261421310222L;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    private Forest currentF;

    @PostConstruct
    private void setup() {
        currentF = new Forest();
    }

    public List<Forest> getForests() {
        return remoteCatalogue.getForests();
    }

    public List<CategoryType> getCategoryTypes() {
        return remoteCatalogue.getCategoryTypes();
    }

    public List<Elf> getElves() {
        return remoteCatalogue.getElves();
    }

    public void addCategory() {
        remoteCatalogue.addForest(currentF);
    }

    public void removeElf(Elf elf) {
        remoteCatalogue.removeElf(elf);
    }

    public void removeForest(Forest forest) {
        remoteCatalogue.removeForest(forest);
    }

    public Forest getCurrentF() {
        return currentF;
    }

    public void setCurrentF(Forest currentF) {
        this.currentF = currentF;
    }
}
