package dataset.auth;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by programowanie on 23.01.2016.
 */

@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CurrentUser {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
