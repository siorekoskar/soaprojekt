package proj;

import entity.Elf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class AddEditElementController implements Serializable {

    private static final long serialVersionUID = -8212968078594377865L;
    private Elf currentElement;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @PostConstruct
    private void setup() {
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
    }
}

