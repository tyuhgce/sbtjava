package main.java.sbt.lessons.lesson3;

import java.util.*;

/**
 * Created by SBTJavastudent on 11.08.2016.
 */
public class Main {

    public static final int END_EXCLUSIVE = 20;

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("bob"));
        personList.add(new Person("frank"));
        personList.add(new Person("rmn"));
        personList.add(new Person("jane"));

        Arrays.asList(
                new Truck(1),
                new Truck(2),
                new Truck(3),
                new Truck(4)
        );

        personList.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        personList.stream().forEach(System.out::println);

    }

}
