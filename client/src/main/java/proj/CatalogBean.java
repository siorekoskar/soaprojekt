package proj;

import entity.CategoryType;
import entity.Elf;
import entity.Forest;
import entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ConversationScoped
@Named
public class CatalogBean implements Serializable {

    private static final long serialVersionUID = 1524342261421310222L;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    private Forest currentCategory;

    @PostConstruct
    private void setup() {
        currentCategory = new Forest();
    }

    public List<Forest> getCategories() {
        return remoteCatalogue.getForests();
    }

    public List<CategoryType> getCategoryTypes() {
        return remoteCatalogue.getCategoryTypes();
    }

    public List<Elf> getElements() {
        return remoteCatalogue.getElves();
    }

    public void addCategory() {
        User remoteUser = remoteCatalogue.getUsers().stream()
                .filter(user -> user.getLogin().equals(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()))
                .findFirst().get();
        currentCategory.setUsersByUserId(remoteUser);
        remoteCatalogue.addForest(currentCategory);
    }

    public void removeElement(Elf elf) {
        remoteCatalogue.removeElf(elf);
    }

    public void removeCategory(Forest forest) {
        remoteCatalogue.removeForest(forest);
    }

    public Forest getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Forest currentCategory) {
        this.currentCategory = currentCategory;
    }
}
