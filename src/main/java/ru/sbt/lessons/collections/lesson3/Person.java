package main.java.ru.sbt.lessons.collections.lesson3;

//import com.sun.istack.internal.NotNull;

/**
 * Created by SBTJavastudent on 11.08.2016.
 */
public class Person {
    private String name;

    public Person(String name) {
        if(name == null)
            throw new NullPointerException("name is null");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return name != null ? name.equals(person.name) : person.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
