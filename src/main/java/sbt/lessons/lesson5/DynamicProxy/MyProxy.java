package main.java.sbt.lessons.lesson5.DynamicProxy;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by SBTJavastudent on 27.08.2016.
 */
interface IPerson {
    String getName();
    void setName(String name);
    void rename(String new_name);
}

@interface Languages {
    String language();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
@interface ForMethod {
    String version();
    int startValue() default 5;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
@interface ForClass {
    enum Priority {
        LOW, MEDIUM, HIGH
    }
    Priority priority() default Priority.MEDIUM;
    String[] tags() default "";
    String createdBy() default "Mkyong";
    String lastModified() default "03/01/2014";
}

@ForClass(
        priority = ForClass.Priority.HIGH,
        tags = {"1", "2", "3"},
        createdBy = "I",
        lastModified = "08/27/2016"
)
class Person implements IPerson {
    private String name ;

    @ForMethod(version = "1.2", startValue = 1)
    public  String getName() {
        return name;
    }
    @ForMethod(version = "1.3", startValue = 2)
    public  void   setName(String name) {
        this.name = name;
    }

    @ForMethod(version = "1.4", startValue = 3)
    public void rename(String new_name) {
        if (!new_name.equals(name))	{
            this.name = new_name;
        }
    }
}

class NeverSleepingEye implements java.lang.reflect.InvocationHandler {
    private Object obj;

    public NeverSleepingEye(Object f1){ obj = f1; }

    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
            throws Throwable {
        System.out.println("NeverSleepingEye invoke : " + method.getName());
        Class<Person> cl = Person.class;
        if (cl.isAnnotationPresent(ForClass.class)) {
            System.out.println("yes!");
        }
        for (Method mthd : obj.getClass().getDeclaredMethods()) {
            // if method is annotated with @Test
            if (method.isAnnotationPresent(ForMethod.class)) {
                Annotation annot = method.getAnnotation(ForMethod.class);
            }
        }

        return method.invoke(obj, args) ;
    }
}


public class MyProxy {

    public static void main(String[] args) {
        Person person = new Person();

        IPerson personproxy = (IPerson) Proxy.newProxyInstance(Person.class.getClassLoader(),
                Person.class.getInterfaces(),
                new NeverSleepingEye(person));

        personproxy.setName("Гриша");
        String h  = personproxy.getName() ;
        personproxy.rename("Вася");
    }

}
