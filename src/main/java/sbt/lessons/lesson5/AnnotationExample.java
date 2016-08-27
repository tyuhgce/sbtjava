package main.java.sbt.lessons.lesson5;

import main.com.homeworks.work2.Truck;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by SBTJavastudent on 27.08.2016.
 */



@interface Format {
    /**
     * Имя поля, которое что то там.
     * @return
     */

    String fieldName() default "type";
    boolean FullDescription() default true;
}

class A {
    @Format(fieldName = "abc")
    public void print(Truck truck) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method printMethod = A.class.getMethod("print", Truck.class);
        Format annotation = printMethod.getAnnotation(Format.class);
        String fieldName = annotation.fieldName();
        Method gettedMethod = A.class.getMethod(getGetterNameForField(fieldName));
        Object value = gettedMethod.invoke(truck);

        System.out.println(String.valueOf(value));

        System.out.println(annotation);
        System.out.println(truck.getId());
    }

    private String getGetterNameForField(String fullName) {
        return "get" +
                fullName.substring(0, 1).toUpperCase() + fullName.substring(1);
    }

}

/*
дз:
Нужно запарсить xml. Если увидел запись такую, как есть в анотации,
то записываем в класс.

 */
@interface Element {
    String name();
}
@interface Attribute {
    String value() default "";
}

@Element(name = "person")
class Person {
    @Attribute("name")
    String name;
    @Attribute
    String phone;
    @Element(name = "children")
    List<Person> children;
}

public class AnnotationExample {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        new A().print(new Truck(1, 2));
    }
}
