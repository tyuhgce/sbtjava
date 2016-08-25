package main.java.sbt.lessons.lesson4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBTJavastudent on 25.08.2016.
 */
public class GarbageCollector {
    public static void main(String[] args) {

        List<String> myStr = new ArrayList<>();
        List<String> myStrOld = new ArrayList<>();

        int i = 0;
        while(true) {
            new String("abc" + i++);
            myStr.add("cba" + i);
            if (i % 10000 == 0) {
                myStr.clear();
                
            }

            if (i % 100_000 == 0)
                myStrOld.add("www" + i);
        }
    }
}
