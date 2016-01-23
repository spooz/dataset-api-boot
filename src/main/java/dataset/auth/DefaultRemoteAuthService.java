package dataset.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by programowanie on 09.01.2016.
 */
@Service("defaultRemoteAuthService")
public class DefaultRemoteAuthService implements RemoteAuthService {


    @Autowired
    private CurrentUser currentUser;

    @Override
    public boolean isAuthorized(String token) {
        authenticateUser(token);
        return true;
    }

    private void authenticateUser(String token) {
        if(token.equals("test1"))
           currentUser.setId(1L);
        else if(token.equals("test2"))
            currentUser.setId(2L);
        else
            currentUser.setId(3L);
    }

}
