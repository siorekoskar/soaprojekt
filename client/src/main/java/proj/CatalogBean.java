package proj;

import entity.Catalog;
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

    private Forest currentForest;
    private Elf currentElf;
    private CategoryType currentCategoryType;
    private List<Catalog> catalogs;
    private Forest currentF;

    public void sendElf() {
        remoteCatalogue.addElf(currentElf);
    }

    @PostConstruct
    private void setup() {
        catalogs = remoteCatalogue.getCatalogs();
        currentElf = new Elf();
        currentF = new Forest();
    }

    public List<Catalog> getCatalogs() {
        return remoteCatalogue.getCatalogs();
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
        catalogs = remoteCatalogue.getCatalogs();
    }

    public void setCurrentForest(Forest f) {
        currentForest = f;
    }

    public Forest getCurrentForest() {
        return currentForest;
    }

    public Elf getCurrentElf() {
        return currentElf;
    }

    public void setCurrentElf(Elf currentElf) {
        this.currentElf = currentElf;
    }

    public void removeElf(Elf elf){
        remoteCatalogue.removeElf(elf);
    }

    public void removeForest(Forest forest){
        remoteCatalogue.removeForest(forest);
    }

    public CategoryType getCurrentCategoryType() {
        return currentCategoryType;
    }

    public void setCurrentCategoryType(CategoryType currentCategoryType) {
        this.currentCategoryType = currentCategoryType;
    }

    public Forest getCurrentF() {
        return currentF;
    }

    public void setCurrentF(Forest currentF) {
        this.currentF = currentF;
    }

    public void updateLabel(){

    }
}
