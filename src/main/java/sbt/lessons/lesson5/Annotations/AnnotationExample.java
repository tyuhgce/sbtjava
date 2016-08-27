package main.java.sbt.lessons.lesson5.Annotations;

import main.com.homeworks.work2.Truck;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by SBTJavastudent on 27.08.2016.
 */



@interface Format {
    String fieldName();
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


//


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
@interface Test {
    //should ignore this test?
    boolean enabled() default true;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
@interface TesterInfo {
    enum Priority {
        LOW, MEDIUM, HIGH
    }
    Priority priority() default Priority.MEDIUM;
    String[] tags() default "";
    String createdBy() default "Mkyong";
    String lastModified() default "03/01/2014";
}

@TesterInfo(
        priority = TesterInfo.Priority.HIGH,
        createdBy = "mkyong.com",
        tags = {"sales","test" }
)
class TestExample {
    @Test
    void testA() {
        if (true)
            throw new RuntimeException("This test always failed");
    }
    @Test(enabled = false)
    void testB() {
        if (false)
            throw new RuntimeException("This test always passed");
    }
    @Test(enabled = true)
    void testC() {
        if (10 > 1) {
            // do nothing, this test always passed.
        }
    }
}


public class AnnotationExample {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Testing...");

        int passed = 0, failed = 0, count = 0, ignore = 0;

        Class<TestExample> obj = TestExample.class;

        // Process @TesterInfo
        if (obj.isAnnotationPresent(TesterInfo.class)) {

            Annotation annotation = obj.getAnnotation(TesterInfo.class);
            TesterInfo testerInfo = (TesterInfo) annotation;

            System.out.printf("%nPriority :%s", testerInfo.priority());
            System.out.printf("%nCreatedBy :%s", testerInfo.createdBy());
            System.out.printf("%nTags :");

            int tagLength = testerInfo.tags().length;
            for (String tag : testerInfo.tags()) {
                if (tagLength > 1) {
                    System.out.print(tag + ", ");
                } else {
                    System.out.print(tag);
                }
                tagLength--;
            }

            System.out.printf("%nLastModified :%s%n%n", testerInfo.lastModified());

        }

        // Process @Test
        for (Method method : obj.getDeclaredMethods()) {

            // if method is annotated with @Test
            if (method.isAnnotationPresent(Test.class)) {

                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;

                // if enabled = true (default)
                if (test.enabled()) {

                    try {
                        method.invoke(obj.newInstance());
                        System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                        failed++;
                    }

                } else {
                    System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
                    ignore++;
                }

            }

        }
        System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);

    }
}
