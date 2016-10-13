package main.java.sbt.lessons.Convertor;

import java.util.HashMap;
import java.util.Map;

public class ConvertImpl implements Convert {

    public static void main(String[] args) {
        ConvertImpl convert = new ConvertImpl();

        convert.AddConvert(Integer.class, t -> t.toString());

        convert.convert(25, String.class);
    }

    private Map<Class, ConvertTo> map;

    public ConvertImpl() {
        this.map = new HashMap<>();
    }

    @Override
    public <T> T convert(Object valueFrom, Class<T> resultClass) {
        ConvertTo<T> convertTo = map.get(valueFrom.getClass());
        if (convertTo != null) {
            return convertTo.convert(valueFrom);
        }
        return null;
    }

    @Override
    public <T, K> void AddConvert(Class<T> cl, ConvertTo<K> convertTo) {
        if (cl == null || convertTo == null) {
            throw new IllegalArgumentException();
        }

        map.put(cl, convertTo);
    }
}
