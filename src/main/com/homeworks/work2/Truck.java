package main.com.homeworks.work2;

/**
 * Created by god on 8/23/2016.
 */
public class Truck {
    private int id;
    private int capacity;

    public Truck(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
