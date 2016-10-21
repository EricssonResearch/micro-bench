package com.ericsson.research.perftest.echo.wildfly;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@Path("/echo")
public class EchoEndpoint {

  @POST
  @Consumes("text/plain")
  @Produces("text/plain")
  public Response doPost(String input) {
    	return Response.ok(input).build();
  }
}
