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
public class ElfConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ValueExpression vex =
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{catalogBean}", CatalogBean.class);

        CatalogBean beers = (CatalogBean) vex.getValue(context.getELContext());
        return beers.getElves().stream()
                .filter(elf -> elf.getElfId().equals(Integer.valueOf(value)))
                .findAny().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Elf elf = (Elf) value;
        return String.valueOf(elf.getElfId());
    }
}
