package com.ericsson.research.perftest.echo.restexpress;

import org.restexpress.Request;
import org.restexpress.Response;

import java.nio.charset.Charset;

public class SampleController
{
	public SampleController()
	{
		super();
	}

	// handles POST requests
	public Object create(Request request, Response response)
	{
		return request.getBody().toString(Charset.defaultCharset());
	}
}
