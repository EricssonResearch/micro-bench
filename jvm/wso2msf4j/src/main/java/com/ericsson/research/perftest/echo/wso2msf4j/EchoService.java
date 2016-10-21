package com.ericsson.research.perftest.echo.wso2msf4j;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/*
 * EchoService resource class
 */
@Path("/EchoService")
public class EchoService {

    @POST
    @Path("echo")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String echo(String body) {
        return body;
    }
}
