package com.ericsson.research.perftest.echo.spark;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.threadPool;
import static spark.Spark.ipAddress;

/**
 * Application
 */
public class Application {
    public static void main(String[] args) {
        port(8080);
	int maxThreads = 128;
	int minThreads = 128;
	int timeOutMillis = 60000;
        ipAddress(args[0]);
	threadPool(maxThreads, minThreads, timeOutMillis);
        post("/EchoService/echo", (req, res) -> req.body());
    }
}
