package dataset.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * Created by programowanie on 23.01.2016.
 */
@Configuration
public class AppConfig {

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }
}
