package dataset.helpers;

import org.springframework.stereotype.Service;

/**
 * Created by programowanie on 09.01.2016.
 */
@Service("defaultAuthService")
public class DefaultAuthService implements AuthService {


    @Override
    public boolean isAuthorized(String token) {
        return true;
    }

    @Override
    public Long getUserId(String token) {
        return 1L;
    }
}
