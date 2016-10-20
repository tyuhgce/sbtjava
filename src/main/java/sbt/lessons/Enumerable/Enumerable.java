package main.java.sbt.lessons.Enumerable;

import java.util.function.Predicate;

/**
 * Created by SBTJavastudent on 20.10.2016.
 */


public interface Enumerable<T> {
    Enumerable<T> where(Predicate predicate);
    <Q> Enumerable<Q> select();

}
