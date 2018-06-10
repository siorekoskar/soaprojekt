package converters;

import entity.ElementType;
import proj.AddEditElementController;

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
                                "#{addEditElementController}", AddEditElementController.class);

        AddEditElementController beers = (AddEditElementController) vex.getValue(context.getELContext());
        return beers.getElementTypes().stream()
                .filter(elementType -> elementType.getElementTypeId().equals(Integer.valueOf(value)))
                .findAny().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ElementType elementType;
        if (value instanceof ElementType) {
            elementType = (ElementType) value;
            return elementType.getElementTypeId().toString();
        }
        return null;
    }
}
