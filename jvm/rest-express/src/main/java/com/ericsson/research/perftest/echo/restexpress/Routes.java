package com.ericsson.research.perftest.echo.restexpress;

import io.netty.handler.codec.http.HttpMethod;

import org.restexpress.RestExpress;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
    {
		server.uri("EchoService/echo", config.getSampleController())
			.method(HttpMethod.POST).noSerialization()
			.name(Constants.Routes.SINGLE_SAMPLE);
	}
}
