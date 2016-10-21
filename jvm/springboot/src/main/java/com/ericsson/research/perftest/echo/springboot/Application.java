package com.ericsson.research.perftest.echo.springboot;

import org.springframework.boot.SpringApplication;

/**
 * Application entry point
 */
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EchoService.class, args);
    }
}