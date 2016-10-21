package com.ericsson.research.perftest.echo.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * AppStart entry point
 */
public class AppStart extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new AppStart().run(args);
    }

    @Override
    public void run(Configuration configuration,
                    Environment environment) {

        environment.jersey().register(new EchoService());
    }

}
