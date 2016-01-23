package dataset.auth;

/**
 * Created by programowanie on 09.01.2016.
 */
public interface RemoteAuthService {

    boolean isAuthorized(String token);
}
