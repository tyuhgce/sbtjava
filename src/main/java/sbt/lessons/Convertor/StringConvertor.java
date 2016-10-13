package main.java.sbt.lessons.Convertor;

/**
 * Created by SBTJavastudent on 13.10.2016.
 */
public class StringConvertor <String> implements ConvertTo<java.lang.String> {
    @Override
    public java.lang.String convert(Object value) {
        return value.toString();
    }
}
