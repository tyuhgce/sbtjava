package com.springinaction.springidol;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by SBTJavastudent on 06.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "conf.xml"
        );

        Performer performer = (Performer) ctx.getBean("duke");
        try {
            performer.perform();
        } catch (PerformanceException e) {
            e.printStackTrace();
        }
    }
}
