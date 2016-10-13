package main.java.sbt.lessons.Convertor;

import java.util.HashMap;
import java.util.Map;

public class ConvertImpl implements Convert {

    public static void main(String[] args) {
        ConvertImpl convert = new ConvertImpl();
        convert.AddConvert(String.class, t -> t.toString());
        String str = convert.convert(25, String.class);
        System.out.println(str);
    }

    private Map<Class, ConvertTo> map;

    public ConvertImpl() {
        this.map = new HashMap<>();
    }

    @Override
    public <T> T convert(Object valueFrom, Class<T> resultClass) {
        ConvertTo<T> convertTo = map.get(resultClass);
        if (convertTo != null) {
            return convertTo.convert(valueFrom);
        }
        return null;
    }

    @Override
    public <T> void AddConvert(Class<T> resultClass, ConvertTo<T> convertTo) {
        if (resultClass == null || convertTo == null) {
            throw new IllegalArgumentException();
        }
        map.put(resultClass, convertTo);
    }

}
