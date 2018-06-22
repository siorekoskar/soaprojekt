package proj;

import entity.CategoryType;
import entity.Elf;
import entity.Forest;
import entity.Role;
import event.ElementEvent;
import jms.ReceiverController;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ConversationScoped
public class CatalogBean implements Serializable {

    private static final long serialVersionUID = 1524342261421310222L;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @Inject
    private ReceiverController receiverController;
    @Inject
    private Event<ElementEvent> elementEvent;

    private List<Forest> categories;
    private List<Elf> elements;

    public List<Forest> getCategories() {
        return categories;
    }

    @PostConstruct
    private void init() {
        receiverController.foo();
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
        List<Forest> categories = remoteCatalogue.getForests();
        List<Elf> bestElements = new ArrayList<>();
        List<List<Elf>> elements = categories.stream()
                .map(Forest::getElvesByForestId)
                .collect(Collectors.toList());

        if (elements == null) {
            return bestElements;
        }

        for (List<Elf> element : elements) {
            if (element.size() == 0) {
                return bestElements;
            }
            Elf bestElement = element.get(0);
            for (Elf element1 : element) {
                if (element1.getPower() > bestElement.getPower()) {
                    bestElement = element1;
                }
            }
            bestElements.add(bestElement);
        }
        return bestElements;
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
