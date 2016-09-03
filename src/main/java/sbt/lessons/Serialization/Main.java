package main.java.sbt.lessons.Serialization;

import java.io.*;

/**
 * Created by SBTJavastudent on 03.09.2016.
 */
class NotSerializableParent {
    public NotSerializableParent(String superData) {
        this.superData = superData;
    }

    @Override
    public String toString() {
        return "NotSerializableParent{" +
                "superData='" + superData + '\'' +
                ", likes=" + likes +
                '}';
    }

    public String getSuperData() {
        return superData;
    }

    public void setSuperData(String superData) {
        this.superData = superData;
    }

    public NotSerializableParent() {
    }

    protected String superData;
    final int likes = 100;
    static int staticVersion = 2;
}

class SerializableChild extends NotSerializableParent implements Serializable {
    String childData;

    @Override
    public String toString() {
        return "SerializableChild{" +
                "childData='" + childData + '\'' +
                "} " + super.toString();
    }

    private void writeObject(ObjectOutputStream out) throws Exception {
        out.defaultWriteObject();
        out.writeUTF(superData);
        out.writeInt(staticVersion);
    }

    private void readObject(ObjectInputStream in) throws Exception {
        in.defaultReadObject();
        superData = in.readUTF();
        staticVersion = in.readInt();
    }

    public SerializableChild(String superData) {
        super(superData);
    }
}

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byos);

        SerializableChild serializableChild = new SerializableChild("123");
        out.writeObject(serializableChild);
        System.out.println(serializableChild);

        InputStream is = new ByteArrayInputStream(byos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(is);
        SerializableChild fromOut = (SerializableChild) in.readObject();
        System.out.println(fromOut);

    }
}