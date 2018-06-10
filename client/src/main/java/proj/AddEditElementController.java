package proj;

import entity.ElementType;
import entity.Elf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
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

    public void sendElement() {
        remoteCatalogue.addElf(currentElement);
        currentElement = new Elf();
    }

    public List<ElementType> getElementTypes() {
        return remoteCatalogue.getElementTypes();
    }

    public String goToEdit(Elf element) {
        this.currentElement = element;
        return "/secure/add-element.xhtml?cdi=" + conversation.getId();
    }
}

