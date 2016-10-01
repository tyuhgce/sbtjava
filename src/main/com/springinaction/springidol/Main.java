package main.com.springinaction.springidol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by SBTJavastudent on 06.08.2016.
 */
public class Main {
    private static final String path = "file:src/main/resources/beans.xml";
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(path);

        Performer performer = (Performer) ctx.getBean("duke");
        try {
            performer.perform();
        } catch (PerformanceException e) {
            e.printStackTrace();
        }
    }
}
