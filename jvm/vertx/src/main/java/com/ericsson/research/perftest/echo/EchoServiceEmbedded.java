package com.ericsson.research.perftest.echo;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class EchoServiceEmbedded {
	public static void main(String[] args) {
		// Create an HTTP server which simply returns the body of the HTTP POST request as a 200 OK response
		VertxOptions options = new VertxOptions();
		options.setWorkerPoolSize(128);
		Vertx vertx = Vertx.vertx(options);
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.post("/EchoService/echo").handler(EchoServiceEmbedded::handleEcho);
		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

	public static void handleEcho(RoutingContext ctx ) {
		HttpServerResponse response = ctx.response();
		String body = ctx.getBodyAsString();
		response.putHeader("Content-length", String.valueOf(body.length()));
		response.write(body);
		response.end();
	}

}
