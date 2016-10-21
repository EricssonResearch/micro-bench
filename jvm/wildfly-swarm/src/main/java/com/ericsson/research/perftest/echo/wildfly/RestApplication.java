package com.ericsson.research.perftest.echo.wildfly;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/EchoService")
public class RestApplication extends Application {
}
