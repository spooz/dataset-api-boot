package dataset.helpers;

/**
 * Created by programowanie on 09.01.2016.
 */
public interface AuthService {

    boolean isAuthorized(String token);
    Long getUserId(String token);
}
