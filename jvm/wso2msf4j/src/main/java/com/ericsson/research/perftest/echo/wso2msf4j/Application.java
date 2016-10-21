package com.ericsson.research.perftest.echo.wso2msf4j;

import org.wso2.msf4j.MicroservicesRunner;

/**
 * Application entry point
 */
public class Application {

    public static void main(String[] args) {
        new MicroservicesRunner()
                .deploy(new EchoService())
                .start();
    }
}
