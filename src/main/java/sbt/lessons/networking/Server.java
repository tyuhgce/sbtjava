package main.java.sbt.lessons.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private List<Connection> connections = new ArrayList<>();

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean create(int port) throws IOException {
        if (port < 1) {
            return false;
        }
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
        serverSocket = new ServerSocket(port);
        return true;
    }

    @Override
    public void run() {
        System.out.println("     server in run");
        if (serverSocket.isClosed()) {
            System.out.println("server is closed");
            return;
        }

        while (true) {
            Socket s;
            try {
                s = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }
            Connection connection = new Connection(s);
            connection.start();
            connections.add(connection);

        }
    }


    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;

        private String name = "";

        public Connection(Socket socket) {
            this.socket = socket;

            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

            } catch (IOException e) {
                e.printStackTrace();
                close();
            }
        }

        public void send(String message) {
            out.print(message);
        }

        @Override
        public void run() {
            int zch = new Random().nextInt(10);
            System.out.println("     Connection is started");
            System.out.println("     Загаданное число: " + zch);
            try {
                //name = in.readLine();
                name = "Hello all on my server";

                synchronized(connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        iter.next().out.println(name + " cames now");
                    }
                }

                String str = "";
                while (true) {
                    str = in.readLine();
                    System.out.println("     Server receive message " + str + " from client");
                    if (str.equals("exit")) break;


                    if (str.equals(String.valueOf(zch))) {
                        out.println("otgadal!");
                    } else {
                        out.println("ne otgadal!");
                    }

//                    System.out.println("     Server receiving to all message. size of connections: " + connections.size());
//                    synchronized(connections) {
//                        Iterator<Connection> iter = connections.iterator();
//                        while(iter.hasNext()) {
//                            iter.next().out.println(name + ": " + str);
//                        }
//                    }
                }

                synchronized(connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        iter.next().out.println(name + " has left");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        public void close() {
            try {
                in.close();
                out.close();
                socket.close();

                connections.remove(this);
                if (connections.size() == 0) {
                    //Server.this.closeAll();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("something went wrong");
            }
        }
    }
}





