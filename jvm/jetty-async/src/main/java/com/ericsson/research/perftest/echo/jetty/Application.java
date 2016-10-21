package com.ericsson.research.perftest.echo.jetty;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.servlet.ServletHandler;

public class Application {

    public void start(String host, int port) {
        QueuedThreadPool threadPool = new QueuedThreadPool(128, 128,60000);

        Server server = new Server(threadPool);

        HttpConfiguration config = new HttpConfiguration();

        ServerConnector http = new ServerConnector(server,new HttpConnectionFactory(config));

        http.setHost(host);
        http.setPort(port);
        server.addConnector(http);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        // Passing in the class for the Servlet allows jetty to instantiate an
        // instance of that Servlet and mount it on a given context path.

        // IMPORTANT:
        // This is a raw Servlet, not a Servlet that has been configured
        // through a web.xml @WebServlet annotation, or anything similar.
        handler.addServletWithMapping(EchoServlet.class, "/*");

        try{
            System.out.format("Starting Echo server at %s:%s\n", host, port);

            server.start();
            System.out.println("Waiting for incoming requests (stop the server with ctrl-c at anytime) ...");
            server.join();
        }catch (Exception ex){
            System.out.println("Error " + ex.toString());
            System.exit(-1);
        }
    }

    public static void main(String[] argv){
        String host;
        int port;

        if (argv.length != 2){
            host = "127.0.0.1";
            port = 8080;
        }
        else {
            host = argv[0];
            port = new Integer(argv[1]);
        }

        Application app = new Application();
        app.start(host, port);
    }
}
