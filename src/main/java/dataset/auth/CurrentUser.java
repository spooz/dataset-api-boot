package dataset.auth;

import org.springframework.stereotype.Component;

/**
 * Created by programowanie on 23.01.2016.
 */

@Component
public class CurrentUser {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
