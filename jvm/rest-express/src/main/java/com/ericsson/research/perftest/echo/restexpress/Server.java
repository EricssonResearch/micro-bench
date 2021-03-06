package com.ericsson.research.perftest.echo.restexpress;

import org.restexpress.RestExpress;
import com.ericsson.research.perftest.echo.restexpress.serialization.SerializationProvider;

public class Server
{
	private static final String SERVICE_NAME = "EchoService";

	private RestExpress server;
	private Configuration config;
	private boolean isStarted = false;

	public Server(Configuration config)
	{
		this.config = config;
		RestExpress.setDefaultSerializationProvider(new SerializationProvider());

		this.server = new RestExpress()
				.setName(SERVICE_NAME)
				.setBaseUrl(config.getBaseUrl())
				.setExecutorThreadCount(config.getExecutorThreadPoolSize());
				// comment out to disable logs
				//.addMessageObserver(new SimpleConsoleLogMessageObserver());

		Routes.define(config, server);
	}

	public Server start()
	{
		if (!isStarted)
		{
			server.bind(config.getPort());
			isStarted = true;
		}

		return this;
	}

	public void awaitShutdown()
	{
		if (isStarted) server.awaitShutdown();
	}

	public void shutdown()
	{
		if (isStarted) server.shutdown();
	}
}
