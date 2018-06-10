package proj;

import java.io.Serializable;

public interface UserDetails extends Serializable {
    String getUserName();
    String getOldPassword();
    String getNewPassword();
}
