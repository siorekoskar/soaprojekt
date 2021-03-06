package proj;

import entity.ElementType;
import entity.Elf;
import event.ElementEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class AddEditElementController implements Serializable {

    private static final long serialVersionUID = -8212968078594377865L;
    private Elf currentElement;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @Inject
    private Conversation conversation;

    @Inject
    private Event<ElementEvent> elementEvent;

    public AddEditElementController() {
        currentElement = new Elf();
    }

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public Elf getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(Elf currentElement) {
        this.currentElement = currentElement;
    }

    public String sendElement() {
        if(currentElement.getElfId() == null) {
            remoteCatalogue.addElf(currentElement);
            currentElement = new Elf();
            fireEvent();
            conversation.end();
        } else {
            remoteCatalogue.changeVariables(currentElement);
            currentElement = new Elf();
            fireEvent();
            conversation.end();
        }
        return "/secure/index.xhtml";
    }

    public String goToNextPage() {
        return "/secure/add-element2.xhtml?cid=" + conversation.getId();
    }

    public List<ElementType> getElementTypes() {
        return remoteCatalogue.getElementTypes();
    }

    public String goToEdit(Elf element) {
        this.currentElement = element;
        return "/secure/add-element.xhtml?cid=" + conversation.getId();
    }

    private void fireEvent() {
        ElementEvent event = new ElementEvent();
        elementEvent.fire(event);
    }
}

