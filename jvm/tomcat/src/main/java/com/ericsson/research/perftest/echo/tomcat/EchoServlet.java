package com.ericsson.research.perftest.echo.tomcat;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, name = "EchoServlet",urlPatterns = {"/EchoService/echo"})
public class EchoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.setTimeout(0);
        Echoer echoer = new Echoer(asyncContext);
        request.getInputStream().setReadListener(echoer);
        response.getOutputStream().setWriteListener(echoer);
    }

    private class Echoer implements ReadListener, WriteListener {
        private final byte[] buffer = new byte[4096];
        private final AsyncContext asyncContext;
        private final ServletInputStream input;
        private final ServletOutputStream output;

        private Echoer(AsyncContext asyncContext) throws IOException {
            this.asyncContext = asyncContext;
            this.input = asyncContext.getRequest().getInputStream();
            this.output = asyncContext.getResponse().getOutputStream();
        }

        public void onDataAvailable() throws IOException {
            while (input.isReady()) {
                if (output.isReady()) {
                    int read = input.read(buffer);
                    output.write(buffer, 0, read);

                } else {
                    return;
                }
            }
        }

        public void onAllDataRead() throws IOException {
            asyncContext.complete();
        }

        public void onWritePossible() throws IOException {
            if (input.isFinished()) {
                onAllDataRead();
            } else {
                onDataAvailable();
            }
        }

        public void onError(Throwable failure) {
            failure.printStackTrace();
        }
    }
}

