package proj;

import entity.Role;
import entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = -4917832544251485340L;

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    private UserDetails userDetails;

    public UserController() {
        userDetails = new UserDetails();
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<User> getUsers() {
        List<User> users = remoteCatalogue.getUsers();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (externalContext.isUserInRole(Role.Administrator.name())) {
            return users;
        } else {
            return Collections.singletonList(users.stream()
                    .filter(user -> user.getLogin().equals(externalContext.getUserPrincipal().getName()))
                    .findFirst()
                    .get());
        }
    }

    public boolean changePassword() {
        boolean isPasswordChanged = remoteCatalogue.changePassword(userDetails);
        userDetails = new UserDetails();
        return isPasswordChanged;
    }

    public boolean canEdit(String username){
        return isUserAdmin() || isUsersCategory(username);
    }

    public boolean isUserAdmin(){
        return FacesContext.getCurrentInstance().getExternalContext()
                .isUserInRole(Role.Administrator.name());
    }

    public boolean isUsersCategory(String username){
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser().equals(username);
    }
}
