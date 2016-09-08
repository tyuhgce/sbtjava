package main.java.sbt.lessons.ClassLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;


public class Main {


    public static class MyClassLoader extends ClassLoader{

        public MyClassLoader(ClassLoader parent) {
            super(parent);
        }

        public Class loadClass(String name) throws ClassNotFoundException {
            if(!"reflection.MyObject".equals(name))
                return super.loadClass(name);

            try {
                String url = "file:C:/data/projects/tutorials/web/WEB-INF/" +
                        "classes/reflection/MyObject.class";

                url = "file:C:/classes/main/java/sbt/lessons/ClassLoader/Pers/Pers.class";
                URL myUrl = new URL(url);
                URLConnection connection = myUrl.openConnection();
                InputStream input = connection.getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int data = input.read();

                while(data != -1){
                    buffer.write(data);
                    data = input.read();
                }

                input.close();

                byte[] classData = buffer.toByteArray();

                return defineClass("Pers",
                        classData, 0, classData.length);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
        //Object person = Main.class.getClassLoader().loadClass("main.java.sbt.lessons.ClassLoader.Pers").newInstance();

        MyClassLoader mcl = new MyClassLoader(Main.class.getClassLoader());
        mcl.loadClass("reflection.MyObject");

        //URLClassLoader urlcl = new URLClassLoader(new URL[]{new File("C:\\classes").toURI().toURL()});
        //urlcl.loadClass("Pers");
}
}
