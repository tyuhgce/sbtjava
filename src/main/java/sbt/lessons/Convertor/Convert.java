package main.java.sbt.lessons.Convertor;

/**
 * Created by SBTJavastudent on 13.10.2016.
 */

public interface Convert {
    <T> T convert(Object ValueFrom, Class<T> resultClass);
    <T> void AddConvert(Class<T> resultClass, ConvertTo<T> convertTo);
}

