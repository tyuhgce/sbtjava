package main.com.homeworks.work2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by god on 8/23/2016.
 */

interface IMyMap<K, V> {
    Collection<V> get(K key);
    void put(K key, V value);
}

public class MyMap <K, V> implements IMyMap<K, V> {
    private int capacity = 2;
    ArrayList<TreeSet<V>> unorderedSet = new ArrayList<>();

    @Override
    public Collection<V> get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }
}
