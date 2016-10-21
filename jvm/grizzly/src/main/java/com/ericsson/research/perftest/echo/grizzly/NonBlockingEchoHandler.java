package com.ericsson.research.perftest.echo.grizzly;

import org.glassfish.grizzly.ReadHandler;
import org.glassfish.grizzly.http.io.NIOReader;
import org.glassfish.grizzly.http.io.NIOWriter;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import java.io.IOException;

/**
 * This handler using non-blocking streams to read POST data and echo it
 * back to the client.
 */
public class NonBlockingEchoHandler extends HttpHandler {

    // -------------------------------------------- Methods from HttpHandler

    @Override
    public void service(final Request request,
                        final Response response) throws Exception {

        final char[] buf = new char[128];
        final NIOReader in = request.getNIOReader(); // return the non-blocking InputStream
        final NIOWriter out = response.getNIOWriter();

        response.suspend();

        // If we don't have more data to read - onAllDataRead() will be called
        in.notifyAvailable(new ReadHandler() {

            @Override
            public void onDataAvailable() throws Exception {
                //System.out.printf("[onDataAvailable] echoing %d bytes\n", in.readyData());
                echoAvailableData(in, out, buf);
                in.notifyAvailable(this);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("[onError]" + t);
                response.resume();
            }

            @Override
            public void onAllDataRead() throws Exception {
                //System.out.printf("[onAllDataRead] length: %d\n", in.readyData());
                try {
                    echoAvailableData(in, out, buf);
                } finally {
                    try {
                        in.close();
                    } catch (IOException ignored) {
                    }

                    try {
                        out.close();
                    } catch (IOException ignored) {
                    }

                    response.resume();
                }
            }
        });

    }

    private void echoAvailableData(NIOReader in, NIOWriter out, char[] buf)
            throws IOException {

        while(in.isReady()) {
            int len = in.read(buf);
            out.write(buf, 0, len);
        }
    }

} // END NonBlockingEchoHandler