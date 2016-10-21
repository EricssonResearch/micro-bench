package ninja.bodyparser;

import ninja.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

@Singleton
public class BodyParserEngineTextPlain implements BodyParserEngine {

    private final Logger logger = LoggerFactory.getLogger(BodyParserEngineTextPlain.class);

    @Override
    public <T> T invoke(Context context, Class<T> classOfT) {
        String result = null;

        if (!classOfT.equals(String.class)) {
            logger.error( "Can parse text/plain only into String. Requested is a {}", classOfT);
            throw new RuntimeException("Can parse text/plain only into String.");
        }

        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(context.getInputStream(), writer, "UTF-8");
            result = writer.toString();
        } catch (IOException ioException) {
            logger.error("Error parsing incoming text/plain", ioException);
        }

        return (T) result;
    }

    @Override
    public String getContentType() {
        return "text/plain";
    }

}