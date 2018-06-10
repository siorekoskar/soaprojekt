package converters;

import entity.User;
import proj.UserController;

import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
@RequestScoped
public class UserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ValueExpression vex =
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{userController}", UserController.class);

        UserController beers = (UserController) vex.getValue(context.getELContext());
        return beers.getUsers().stream()
                .filter(forest -> forest.getLogin().equals(value))
                .findAny().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        User forest = (User) value;
        return forest.getLogin();
    }
}
