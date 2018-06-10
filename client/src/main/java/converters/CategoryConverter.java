package converters;

import entity.CategoryType;
import proj.CatalogBean;

import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
@RequestScoped
public class CategoryConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ValueExpression vex =
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{catalogBean}", CatalogBean.class);

        CatalogBean beers = (CatalogBean) vex.getValue(context.getELContext());
        return beers.getCategoryTypes().stream()
                .filter(categoryType -> categoryType.getCategoryTypeId().equals(Integer.valueOf(value)))
                .findAny().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        CategoryType categoryType = (CategoryType) value;
        return String.valueOf(categoryType.getCategoryTypeId());
    }
}
