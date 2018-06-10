package converters;

import entity.Elf;
import proj.CatalogBean;

import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;


@Named
@RequestScoped
public class ElementConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ValueExpression vex =
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{catalogBean}", CatalogBean.class);

        CatalogBean beers = (CatalogBean) vex.getValue(context.getELContext());
        return beers.getElements().stream()
                .filter(element -> element.getElfId().equals(Integer.valueOf(value)))
                .findAny().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Elf element = (Elf) value;
        return String.valueOf(element.getElfId());
    }
}
