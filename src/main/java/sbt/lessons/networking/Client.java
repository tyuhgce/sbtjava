package main.java.sbt.lessons.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.SecureRandom;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Resender r;

    public boolean connection(String hostname, int port) throws IOException {
        if (port < 1 || hostname == null) {
            return false;
        }

        try {
            socket = new Socket(hostname, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            return false;
        }
        System.out.println("Client start");
        //r = new Resender();
        //r.start();

        receiveAllMessage();

        return true;
    }

    public void receiveAllMessage() throws IOException {
        boolean choose = true;
        int i = 0;

        while (true) {
            if (choose) {
                out.println(i);
                i++;
            } else {
                String str = in.readLine();
                System.out.println("client receive message: " + str + "\n");
                if (str.equals("otgadal!")) {
                    return;
                }
            }
            choose = !choose;
        }

//        while (true) {
//            String str = in.readLine();
//            System.out.println("client receive message: " + str + "\n");
//            if (str.equals("otgadal!")) {
//                r.setStop();
//                return;
//            }
//        }
    }

    private class Resender extends Thread {

        private boolean stoped;

        public void setStop() {
            stoped = true;
        }

        @Override
        public void run() {
            int i = 0;
            while (!stoped) {
                //String str = in.readLine();
                //String str = StringUtils.getRandomString();
                System.out.println("Clinet send random string: " + i);
                out.println(i);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

    }
}
