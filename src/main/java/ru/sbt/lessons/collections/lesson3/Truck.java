package main.java.ru.sbt.lessons.collections.lesson3;

/**
 * Created by SBTJavastudent on 13.08.2016.
 */
public class Truck {
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Truck(int capacity) {
        this.capacity = capacity;
        id = generalId++;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck)) return false;

        Truck truck = (Truck) o;

        if (getCapacity() != truck.getCapacity()) return false;
        return id == truck.id;

    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    private int capacity;

    private long id;
    private static long generalId = 0;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    // Нужно, чтобы из Map можно выбло вытянуть не только по ID конкретную машину
    // но и категория машин по парке(например Камаз, Вольво и тд)

    // Нужно хранить в мапе не саму машину, а массив машин, который можно будет тянуть
    // по типу.
    // multimap
}
