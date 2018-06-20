package proj;

import entity.CategoryType;
import entity.Elf;
import entity.Forest;
import entity.Role;
import event.ElementEvent;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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

    private List<Forest> categories;
    private List<Elf> elements;

    public List<Forest> getCategories() {
        return categories;
    }

    @Inject
    private Event<ElementEvent> elementEvent;

    @PostConstruct
    private void init() {
        categories = remoteCatalogue.getForests();
        elements = remoteCatalogue.getElves();
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
        return elements;
    }

    public void removeElement(Elf elf) {
        remoteCatalogue.removeElf(elf);
        fireEvent();
    }

    public void removeCategory(Forest forest) {
        remoteCatalogue.removeForest(forest);
        fireEvent();
    }

    public List<Elf> bestElements() {
        return remoteCatalogue.getForests().stream()
                .map(Forest::getElvesByForestId)
                .map(elves -> elves.stream()
                        .reduce((a, b) -> a.getPower() > b.getPower() ? a : b).get())
                .collect(Collectors.toList());
    }

    public void updateAllElements(@Observes ElementEvent elementEvent) {
        categories = remoteCatalogue.getForests();
        elements = remoteCatalogue.getElves();
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/reload", new FacesMessage(categories.toString()));
    }

    private void fireEvent() {
        ElementEvent event = new ElementEvent();
        elementEvent.fire(event);
    }
}
