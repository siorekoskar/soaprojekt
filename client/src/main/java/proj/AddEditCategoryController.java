package proj;

import entity.CategoryType;
import entity.Forest;
import entity.User;
import event.ElementEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class AddEditCategoryController implements Serializable {

    private static final long serialVersionUID = 7428840868353047821L;
    private Forest currentCategory;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @Inject
    private Conversation conversation;

    @Inject
    private Event<ElementEvent> elementEvent;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public AddEditCategoryController() {
        currentCategory = new Forest();
    }

    public Forest getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Forest currentCategory) {
        this.currentCategory = currentCategory;
    }

    public String sendCategory() {
        User remoteUser = remoteCatalogue.getUsers().stream()
                .filter(user -> user.getLogin().equals(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()))
                .findFirst().get();
        currentCategory.setUsersByUserId(remoteUser);
        remoteCatalogue.addForest(currentCategory);
        this.currentCategory = new Forest();
        fireEvent();
        conversation.end();
        return "/secure/index.xhtml";
    }

    public List<CategoryType> getCategoryTypes() {
        return remoteCatalogue.getCategoryTypes();
    }

    public String goToEdit(Forest category) {
        this.currentCategory = category;
        return "/secure/add-category.xhtml?cdi=" + conversation.getId();
    }

    private void fireEvent() {
        ElementEvent event = new ElementEvent();
        elementEvent.fire(event);
    }
}
