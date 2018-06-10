package proj;

import entity.ElementType;
import entity.Elf;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
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

    public AddEditElementController() {
        currentElement = new Elf();
    }

    public Elf getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(Elf currentElement) {
        this.currentElement = currentElement;
    }

    public void sendElf() {
        remoteCatalogue.addElf(currentElement);
        currentElement = new Elf();
    }

    public List<ElementType> getElementTypes() {
        return remoteCatalogue.getElementTypes();
    }

    public String goToEdit(Elf elf) {
        this.currentElement = elf;
        return "/secure/add-element.xhtml";
    }
}

