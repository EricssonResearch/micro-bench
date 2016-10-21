package com.ericsson.research.perftest.echo.grizzly;

import java.io.IOException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;

public class EchoServer {
    public static void main(String[] args) throws InterruptedException {

        // create a basic server that listens on port 8080.
        final HttpServer server = HttpServer.createSimpleServer();

        final ServerConfiguration config = server.getServerConfiguration();

        // Map the path, /echo, to the NonBlockingEchoHandler
        config.addHttpHandler(new NonBlockingEchoHandler(), "/EchoService/Echo");

        try {
            server.start();
            System.out.println("Press any key to stop the server...");

            Thread.currentThread().join();
        } catch (IOException ioe) {
        } finally {
            server.shutdownNow();
        }
    }
}
