package proj;

import entity.CategoryType;
import entity.Elf;
import entity.Forest;
import entity.Role;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ConversationScoped
public class CatalogBean implements Serializable {

    private static final long serialVersionUID = 1524342261421310222L;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    public List<Forest> getCategories() {
        return remoteCatalogue.getForests();
    }

    public List<Forest> getUserCategories() {
        String remoteUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Role.Administrator.name())) {
            return getCategories();
        }
        return remoteCatalogue.getForests().stream()
                .filter(category -> category.getUsersByUserId().getLogin().equals(remoteUser))
                .collect(Collectors.toList());
    }

    public List<CategoryType> getCategoryTypes() {
        return remoteCatalogue.getCategoryTypes();
    }

    public List<Elf> getElements() {
        return remoteCatalogue.getElves();
    }

    public void removeElement(Elf elf) {
        remoteCatalogue.removeElf(elf);
    }

    public void removeCategory(Forest forest) {
        remoteCatalogue.removeForest(forest);
    }

    public List<Elf> bestElements() {
        return remoteCatalogue.getForests().stream()
                .map(Forest::getElvesByForestId)
                .map(elves -> elves.stream()
                        .reduce((a, b) -> a.getPower() > b.getPower() ? a : b).get())
                .collect(Collectors.toList());
    }

//    public void updateBestElementFromEachCategoryList(@Observes ElementEvent elementEvent) {
//        bestElementFromEachCategoryList = categoryService.getBestElementFromEachCategory();
//        EventBus eventBus = EventBusFactory.getDefault().eventBus();
//        eventBus.publish("/notify", new FacesMessage(bestElementFromEachCategoryList.toString()));
//    }
}
