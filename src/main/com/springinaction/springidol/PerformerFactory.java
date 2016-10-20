package main.com.springinaction.springidol;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * Created by SBTJavastudent on 20.10.2016.
 */

@Configurable
public class PerformerFactory {
    @Bean(name="duke")
    public static Performer getJunglerPerformer() {
        return new Juggler();
    }
}
