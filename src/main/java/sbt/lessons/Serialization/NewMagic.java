package main.java.sbt.lessons.Serialization;

import java.io.*;

/**
 * Created by SBTJavastudent on 03.09.2016.
 */
class Student implements Serializable {
    private static final long serialVersionUID = 1;
    public int id;
}

class NewStudent implements Serializable  {
    private static final long serialVersionUID = 1;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private int id;
}


public class NewMagic {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

            fos = new FileOutputStream("myfile");
            out = new ObjectOutputStream(fos);
            out.writeObject(new Student());
            out.close();

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        fis = new FileInputStream("myfile");
        ois = new ObjectInputStream(fis);
        Student ns = (Student) ois.readObject();

    }
}
