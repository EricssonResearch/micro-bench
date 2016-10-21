package com.ericsson.research.perftest.echo.dropwizard;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Resource class
 */
@Path("/EchoService")
public class EchoService {

    @POST
    @Path("echo")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String echoPost(String body) {
        return body;
    }

}
