package proj;

import filter.DomainFilter;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
public class Logout implements Serializable {
    private static final long serialVersionUID = 8225442075506600891L;

    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String name = externalContext.getUserPrincipal().getName();
        DomainFilter.getJsessions().remove(name);
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/blank-page.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        externalContext.invalidateSession();
    }
}
