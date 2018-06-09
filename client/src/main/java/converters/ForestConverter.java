package converters;

import entity.Forest;
import proj.CatalogBean;

import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
@RequestScoped
public class ForestConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ValueExpression vex =
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{catalogBean}", CatalogBean.class);

        CatalogBean beers = (CatalogBean) vex.getValue(context.getELContext());
        return beers.getForests().stream()
                .filter(forest -> forest.getForestId().equals(Integer.valueOf(value)))
                .findAny().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Forest forest = (Forest) value;
        return String.valueOf(forest.getForestId());
    }
}
