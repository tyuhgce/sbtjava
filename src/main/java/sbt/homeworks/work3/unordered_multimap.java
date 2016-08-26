package main.java.sbt.homeworks.work3;

import java.util.*;
import java.util.function.Function;

/**
 * Created by SBTJavastudent on 25.08.2016.
 */


public class unordered_multimap<K, V> implements Map<K, Collection<V>> {
    Map<K, Collection<V>> innerMap;
    Function<K, Integer> getHashCode;

    public unordered_multimap(Function<K, Integer> f) {
        getHashCode = f;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Collection<V> get(Object key) {
        return null;
    }

    @Override
    public Collection<V> put(K key, Collection<V> value) {
        return null;
    }

    @Override
    public Collection<V> remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends Collection<V>> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<Collection<V>> values() {
        return null;
    }

    @Override
    public Set<Entry<K, Collection<V>>> entrySet() {
        return null;
    }
}


class unordered_multimap2<K, V> implements Map<K, V> {

    private List<Collection<V>> innerList;

    private int capacity = 4;
    private Function<K, Integer> getHashCode;

    public int getCapacity() {
        return capacity;
    }
    public unordered_multimap setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    private void setHashCode(Function<K, Integer> f ) {
        getHashCode = f;
    }

    public unordered_multimap(int capacity) {
        this.capacity = capacity;
        setHashCode(K::hashCode);
    }
    public unordered_multimap() {
        innerList = new ArrayList<>(capacity);
        setHashCode(K::hashCode);
    }
    public unordered_multimap(Function<K, Integer> hashCode)
    {
        if (hashCode != null) {
            getHashCode = hashCode;
        }
    }



    @Override
    public int size() {
        return innerList.size();
    }

    @Override
    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if (innerList.get(getHashCode.apply(((K) key)) % capacity) != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return innerList
                .stream()
                .anyMatch( s -> s.contains(value) );
    }

    @Override
    public V get(Object key) {
        return getMulti(key)
                .stream()
                .filter(s -> s.equals((K)key))
                .findFirst()
                .get();
    }

    public Collection<V> getMulti(Object key) {
        return innerList
                .get(getHashCode.apply(((K) key)) % capacity);
    }

    @Override
    public V put(K key, V value) {

        return null;
    }

    @Override
    public V remove(Object key) {

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {

        return null;
    }

    @Override
    public Collection<V> values() {

        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {

        return null;
    }
}
