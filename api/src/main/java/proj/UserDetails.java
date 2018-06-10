package proj;

import entity.User;

import java.io.Serializable;

public class UserDetails implements Serializable {
    private static final long serialVersionUID = 2545308528362461115L;

    private String newPassword;
    private String oldPassword;
    private String userName;
    private User user;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userName = user.getLogin();
    }
}
