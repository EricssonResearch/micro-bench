package com.ericsson.research.perftest.echo.undertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class UndertowServer {


        public static void main(final String[] args) {
            Undertow server = Undertow.builder()
                    .addHttpListener(8080, args[0])
                    .setWorkerThreads(128)
                    .setIoThreads(16)
                    .setHandler( new HttpHandler() {
                        public void handleRequest(final HttpServerExchange exchange) throws Exception {

                            if (exchange.isInIoThread()) {
                                exchange.dispatch(this);
                                return;
                            }

                            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                            exchange.startBlocking();
                            byte[] buffer = new byte[1024];
                            final ByteArrayOutputStream b = new ByteArrayOutputStream();
                            int r;
                            final OutputStream outputStream = exchange.getOutputStream();
                            final InputStream inputStream = exchange.getInputStream();
                            while ((r = inputStream.read(buffer)) > 0) {
                                b.write(buffer, 0, r);
                            }
                            outputStream.write(b.toByteArray());
                            outputStream.close();
                            exchange.endExchange();

                        }
                    }).build();
            server.start();
        }

}
