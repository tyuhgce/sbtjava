package main.java.sbt.lessons.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) throws IOException {
        int port = Const.DEFAULT_PORT;

        if (args.length != 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {}
        }

        Server server = new Server();
        server.create(port);
        server.start();

        Client client = new Client();
        client.connection("localhost", port);


    }
}


