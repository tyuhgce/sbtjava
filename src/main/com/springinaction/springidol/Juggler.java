package main.com.springinaction.springidol;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * Created by SBTJavastudent on 06.08.2016.
 */

@Component("duke")
public class Juggler implements Performer {
    private int beanBags = 3;

    public Juggler() {}
    public Juggler(int beanBags) {
        this.beanBags = beanBags;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    private boolean value = false;

    @Override
    public void perform() throws PerformanceException {
        System.out.println("JUGGLING " + beanBags + " BEANBAGS" + " " + value);
    }
}
