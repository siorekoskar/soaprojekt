package converters;

import entity.Forest;
import proj.RemoteCatalogue;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;


@Named
@RequestScoped
public class ForestConverter implements Converter {

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return remoteCatalogue.getForests().stream()
                .filter(forest -> forest.getForestId().equals(Integer.valueOf(value)))
                .findFirst().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Forest forest = (Forest) value;
        return String.valueOf(forest.getForestId());
    }
}
