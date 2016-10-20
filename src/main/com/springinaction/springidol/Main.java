package main.com.springinaction.springidol;

import main.com.springinaction.springidol.customer.CustomerDao;
import main.com.springinaction.springidol.customer.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by SBTJavastudent on 06.08.2016.
 */

//http://websystique.com/spring/spring-dependency-injection-annotation-beans-auto-wiring-using-autowired-qualifier-resource-annotations-configuration/

public class Main {
    private static final String path = "file:src/main/resources/beans.xml";
    public static void main(String[] args) {
        //ApplicationContext ctx = new ClassPathXmlApplicationContext(path);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Performer performer = (Performer) ctx.getBean("duke");
        CustomerService customerService = (CustomerService) ctx.getBean("SbtService");

        try {
            performer.perform();
            String t = customerService.getNameById(1);
            System.out.println(t);
        } catch (PerformanceException e) {
            e.printStackTrace();
        }
    }
}
